import axios from 'axios';
import store from '../store/index'

export default function setup() {

  axios.interceptors.request.use(

    request => {
      const token = store.state.token;
      if(token) {
        request.headers.Authorization = `Bearer ${token}`;
      }
      return request;
    },

    error => {
      return Promise.reject(error);
    }
  );

  axios.defaults.baseURL = "http://localhost:8080"
}
