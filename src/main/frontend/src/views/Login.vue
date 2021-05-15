<template>
  <div class="container signup">
    <h1>ログイン</h1>

    <div v-if="error" class="alert alert-danger">
      メールアドレスまたはパスワードが誤っています。
    </div>

    <div class="form-group row">
      <label class="col-sm-2 col-form-label">メールアドレス <span class="badge badge-danger">必須</span></label>
      <div class="col-sm-10">
        <input type="email" class="form-control" :class="{ 'is-invalid' : getErrorMessage('email') }" v-model="customer.email">
        <div :class="{ 'invalid-feedback' : getErrorMessage('email') }">{{getErrorMessage('email')}}</div>
      </div>
    </div>

    <div class="form-group row">
      <label class="col-sm-2 col-form-label">パスワード <span class="badge badge-danger">必須</span></label>
      <div class="col-sm-10">
        <input type="password" class="form-control" :class="{ 'is-invalid' : getErrorMessage('password') }" v-model="customer.password">
        <div :class="{ 'invalid-feedback' : getErrorMessage('password') }">{{getErrorMessage('password')}}</div>
      </div>
    </div>

    <ul class="firebaseui-idp-list">
      <li class="firebaseui-list-item">
        <button  @click="login" class="firebaseui-idp-button mdl-button mdl-js-button mdl-button--raised firebaseui-idp-password firebaseui-id-idp-button" data-provider-id="password" data-upgraded=",MaterialButton">
          <span class="firebaseui-idp-icon-wrapper"><img class="firebaseui-idp-icon" alt="" src="https://www.gstatic.com/firebasejs/ui/2.0.0/images/auth/mail.svg"></span>
          <span class="firebaseui-idp-text firebaseui-idp-text-long">メールでログイン</span>
          <span class="firebaseui-idp-text firebaseui-idp-text-short">メールアドレス</span>
        </button>
      </li>
      <li class="firebaseui-list-item">
        <button @click="glogin" class="firebaseui-idp-button mdl-button mdl-js-button mdl-button--raised firebaseui-idp-google firebaseui-id-idp-button" data-provider-id="google.com" data-upgraded=",MaterialButton">
          <span class="firebaseui-idp-icon-wrapper"><img class="firebaseui-idp-icon" alt="" src="https://www.gstatic.com/firebasejs/ui/2.0.0/images/auth/google.svg"></span>
          <span class="firebaseui-idp-text firebaseui-idp-text-long">Google でログイン</span>
          <span class="firebaseui-idp-text firebaseui-idp-text-short">Google</span>
        </button>
      </li>
    </ul>

    <hr>

    <ul class="firebaseui-idp-list">
      <li class="firebaseui-list-item">
        初めてのご利用ですか？
      </li>
      <li class="firebaseui-list-item">
        <router-link to="/signup">
          アカウントを作成する
        </router-link>
      </li>
    </ul>
  </div>
</template>

<script>
import axios from 'axios';
import firebase from 'firebase/app';
require("firebaseui-ja/dist/firebaseui.css");

export default {

  // **************************************************************************
  // * データ
  // **************************************************************************
  data: function() {
    return {
      error: null
    }
  },

  // **************************************************************************
  // * 算出プロパティ
  // **************************************************************************
  computed: {

    // ========================================================================
    // 顧客
    // ========================================================================
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
    // ログイン
    // ========================================================================
    login: async function() {

      // メールアドレスとパスワードを使って認証
      firebase.auth().signInWithEmailAndPassword(this.customer.email, this.customer.password)
      .then(async (userCredential) => {

        let customer = await this.getCustomer()
        if (!customer.id) {
          this.customer.id = userCredential.user.uid
          this.customer.name = userCredential.user.displayName
          this.customer.email = userCredential.user.email
          this.createCustomer()
        }

        // 認証後のページに遷移
        this.$router.push('/')
      })

      .catch((error) => {
        this.error = error
        console.log(error.message);
      });
    },

    // ========================================================================
    // Googleログイン
    // ========================================================================
    glogin: async function() {

      const provider = new firebase.auth.GoogleAuthProvider();
      firebase.auth().signInWithPopup(provider)
      .then(async (userCredential) => {

        let customer = await this.getCustomer()
        if (!customer.id) {
          this.customer.id = userCredential.user.uid
          this.customer.name = userCredential.user.displayName
          this.customer.email = userCredential.user.email
          this.createCustomer()
        }

        // 認証後のページに遷移
        this.$router.push('/')
      })

      .catch((error) => {
        this.error = error
        console.log(error.message);
      });
    },

    // ========================================================================
    // ユーザ登録
    // ========================================================================
    createCustomer: async function() {

      // HTTPリクエスト送信
      await axios.post('/api/open/customers/', this.customer)
      .then(response => {
        this.customer = response.data
      })

      .catch(error => {
        this.errors = error.response.data.errors
      })
    },
  },
};
</script>
