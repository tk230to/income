<template>
  <div class="container">
    <h1>サインアップ</h1>

    <div class="form-group row">
      <label class="col-sm-2 col-form-label">ユーザ名 <span class="badge badge-danger">必須</span></label>
      <div class="col-sm-10">
        <input type="text" class="form-control" :class="{ 'is-invalid' : getErrorMessage('name') }" v-model="customer.name">
        <div :class="{ 'invalid-feedback' : getErrorMessage('name') }">{{getErrorMessage('name')}}</div>
      </div>
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

    <b-button variant="outline-primary" @click="signUp">新しいアカウントを作成</b-button>
  </div>
</template>

<script>
import firebase from 'firebase/app';
import axios from 'axios';

export default {

  // **************************************************************************
  // * データ
  // **************************************************************************
  data: function() {
    return {
      customer: {},
      errors: null
    };
  },

  // **************************************************************************
  // * メソッド
  // **************************************************************************
  methods: {

    // ========================================================================
    // サインアップ
    // ========================================================================
    signUp: function() {

      this.validateCustomer()
      .then(() => {
        this.createFirebaseUser()
      })

      .catch((error) => {
        console.log(error.message);
      });
    },

    // ========================================================================
    // 入力チェック
    // ========================================================================
    validateCustomer: async function() {

      // HTTPリクエスト送信
      await axios.post('/api/open/customers/validate', this.customer)
      .then(response => {
        console.log(response)
      })

      .catch(error => {
        this.errors = error.response.data.errors
      })
    },

    // ========================================================================
    // Firebaseユーザ登録
    // ========================================================================
    createFirebaseUser: function() {

      firebase.auth().createUserWithEmailAndPassword(this.customer.email, this.customer.password)
      .then((userCredential) => {
        this.customer.id = userCredential.user.uid
        this.createCustomer()
      })

      .catch((error) => {
        alert(error.message);
      });
    },

    // ========================================================================
    // ユーザ登録
    // ========================================================================
    createCustomer: async function() {

      // HTTPリクエスト送信
      await axios.post('/api/open/customers/', this.customer)
      .then(response => {
        console.log(response)
        this.$router.push('/')
      })

      .catch(error => {
        this.errors = error.response.data.errors
      })
    }
  }
};
</script>
