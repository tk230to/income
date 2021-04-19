export default {

  // **************************************************************************
  // * メソッド
  // **************************************************************************
  methods: {

    // ========================================================================
    // Base64デコード
    // ========================================================================
    base64Encode: function(...parts) {

      if (!file) {
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
    },

    // ========================================================================
    // Base64デコード
    // ========================================================================
    base64DecodeAsBlob: function (text, type = "text/plain;charset=UTF-8") {
      return fetch(`data:${type};base64,` + text).then(response => response.blob());
    },
  }
}
