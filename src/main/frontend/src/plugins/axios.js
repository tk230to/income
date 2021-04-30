import axios from 'axios';
import store from '../store/index'

export default function setup() {

  // ========================================================================
  // リクエスト時処理
  // ========================================================================
  axios.interceptors.request.use(
    async request => {
      await encodeImage(request.data)

      const token = store.state.customer.token;
      if(token) {
        request.headers.Authorization = `Bearer ${token}`;
      }

      return request;
    },

    error => {
      return Promise.reject(error);
    }
  );

  // ========================================================================
  // レスポンス時処理
  // ========================================================================
  axios.interceptors.response.use(
    async response => {
      await decodeImage(response.data)
      return response
    },

    error => {
      return Promise.reject(error);
    }
  );

  // ========================================================================
  // リクエスト先デフォルトURL
  // ========================================================================
  axios.defaults.baseURL = "http://localhost:8080"
}

// ========================================================================
// 画像エンコード
// ========================================================================
async function encodeImage(data) {
  if (typeof data === 'object') {
   if (Array.isArray(data)) {
     for (let element of data) {
       encodeImage(element)
     }
   } else {
     for (let key in data) {
       if (key === "image") {
         data.image = await base64Encode(data.image)
       }
       encodeImage(data[key])
     }
   }
  }
}

// ========================================================================
// Base64エンコード
// ========================================================================
function base64Encode(...parts) {

  if (!parts) {
   return null
  }

  return new Promise(resolve => {
   const reader = new FileReader();
   reader.onload = () => {
     const offset = reader.result.indexOf(",") + 1;
     resolve(reader.result.slice(offset));
   };
   reader.readAsDataURL(new Blob(parts));
  });
}

// ========================================================================
// 画像デコード
// ========================================================================
async function decodeImage(data) {
  if (typeof data === 'object') {
    if (Array.isArray(data)) {
      for (let element of data) {
        decodeImage(element)
      }
    } else {
      for (let key in data) {
        if (key === "image") {
          data.image = await base64DecodeAsBlob(data.image, data.imageType)
        }
        decodeImage(data[key])
      }
    }
  }
}

// ========================================================================
// Base64デコード
// ========================================================================
function base64DecodeAsBlob(text, type = "text/plain;charset=UTF-8") {
  return fetch(`data:${type};base64,` + text).then(response => response.blob());
}
