export default function ({ $axios }) {
    $axios.onRequest(config => {
        const token = localStorage.getItem('token');
        if (token) {
        // ローカルストレージにトークンがあったら、リクエストヘッダ（Authorization）に付与
        config.headers.common['Authorization'] = "Bearer " + token;
        }
    })
}