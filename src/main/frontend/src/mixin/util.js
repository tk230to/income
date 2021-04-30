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
    // 画像ファイルソース取得
    // ========================================================================
    getImageSrc: function(image) {

      if (!image || !image.type) {
        return ""
      }

      return URL.createObjectURL(image)
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

      if (firebase.auth().currentUser) {
        this.customer.token = await firebase.auth().currentUser.getIdToken(true)
      }
      return this.customer
    },
  }
}
