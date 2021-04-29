import axios from 'axios';
import store from '../store/index'

export default function setup() {

  // ========================================================================
  // リクエスト時処理
  // ========================================================================
  axios.interceptors.request.use(

    request => {
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
  // リクエスト先デフォルトURL
  // ========================================================================
  axios.defaults.baseURL = "http://localhost:8080"
}
