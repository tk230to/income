<template>
  <div class="container signup">
    <h1>ログイン</h1>

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

    <b-button variant="outline-primary" @click="login">ログイン</b-button>
    <b-button variant="outline-primary" @click="glogin">Googleログイン</b-button>
  </div>
</template>

<script>
import firebase from "firebase/app";
import { mapActions } from "vuex";

export default {

  // **************************************************************************
  // * データ
  // **************************************************************************
  data: function() {
    return {
      customer: {},
    };
  },

  // **************************************************************************
  // * メソッド
  // **************************************************************************
  methods: {

    ...mapActions([
      "setCustomer",
    ]),

    // ========================================================================
    // ログイン
    // ========================================================================
    login: async function() {
      try {

        // メールアドレスとパスワードを使って認証
        await firebase.auth().signInWithEmailAndPassword(this.customer.email, this.customer.password)
        await this.setCurrentUser(firebase.auth().currentUser)

        // 認証後のページに遷移
        this.$router.push('/')
      } catch (e) {
        // 認証エラー、トークン取得エラー時
        console.log(e)
      }
    },

    // ========================================================================
    // Googleログイン
    // ========================================================================
    glogin: async function() {
      try {
        const provider = new firebase.auth.GoogleAuthProvider();
        await firebase.auth().signInWithPopup(provider);
        await this.setCurrentUser(firebase.auth().currentUser)

        // 認証後のページに遷移
        this.$router.push('/')
      } catch (e) {
        // 認証エラー、トークン取得エラー時
        console.log(e)
      }
    },

    // ========================================================================
    // ユーザ設定
    // ========================================================================
    setCurrentUser: async function() {
      this.getCurrentCustomer()
    }
  }
};
</script>
