import axios from 'axios';
import firebase from "firebase/app";

export default {

  // **************************************************************************
  // * 算出プロパティ
  // **************************************************************************
  computed: {
    customer: {
      get () {
        return this.$store.state.customer
      },
      set (value) {
        this.$store.commit('setCustomer', value)
      }
    },
  },

  // **************************************************************************
  // * メソッド
  // **************************************************************************
  methods: {

    // ========================================================================
    // Base64デコード
    // ========================================================================
    base64Encode: function(...parts) {

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
    },

    // ========================================================================
    // Base64デコード
    // ========================================================================
    base64DecodeAsBlob: function (text, type = "text/plain;charset=UTF-8") {
      return fetch(`data:${type};base64,` + text).then(response => response.blob());
    },

    // ========================================================================
    // エラーメッセージ取得
    // ========================================================================
    getErrorMessage: function(fieldName) {

      // エラーが無い場合
      if (!this.errors) {
        return ""
      }

      for (let error of this.errors) {
        if (error.field === fieldName) {
          return error.defaultMessage
        }
      }
    },

    // ========================================================================
    // ユーザ取得
    // ========================================================================
    getCurrentCustomer: async function() {

      let customer
      let id
      if (firebase.auth().currentUser) {
        id = firebase.auth().currentUser.uid
      }
      await axios.get('/api/open/customers/' + id)
      .then(function (response) {
        customer = response.data
      })
      this.customer = customer

      // 画像をBase64デコード
      for (let cartItem of customer.cartItems) {
        cartItem.item.image = await this.base64DecodeAsBlob(cartItem.item.image, cartItem.item.imageType)
      }

      if (firebase.auth().currentUser) {
        customer.token = await firebase.auth().currentUser.getIdToken(true)
      }
      this.customer = customer
      return customer
    },
  }
}
