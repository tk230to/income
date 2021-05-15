<template>
  <div class="container">
    <h1>アカウントを作成</h1>

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

    <ul class="firebaseui-idp-list">
      <li class="firebaseui-list-item">
        <button  @click="signUp" class="firebaseui-idp-button mdl-button mdl-js-button mdl-button--raised firebaseui-idp-password firebaseui-id-idp-button" data-provider-id="password" data-upgraded=",MaterialButton">
          <span class="firebaseui-idp-icon-wrapper"><img class="firebaseui-idp-icon" alt="" src="https://www.gstatic.com/firebasejs/ui/2.0.0/images/auth/mail.svg"></span>
          <span class="firebaseui-idp-text firebaseui-idp-text-long">メールで登録</span>
          <span class="firebaseui-idp-text firebaseui-idp-text-short">メールアドレス</span>
        </button>
      </li>
      <li class="firebaseui-list-item">
        <button @click="gSignUp" class="firebaseui-idp-button mdl-button mdl-js-button mdl-button--raised firebaseui-idp-google firebaseui-id-idp-button" data-provider-id="google.com" data-upgraded=",MaterialButton">
          <span class="firebaseui-idp-icon-wrapper"><img class="firebaseui-idp-icon" alt="" src="https://www.gstatic.com/firebasejs/ui/2.0.0/images/auth/google.svg"></span>
          <span class="firebaseui-idp-text firebaseui-idp-text-long">Google で登録</span>
          <span class="firebaseui-idp-text firebaseui-idp-text-short">Google</span>
        </button>
      </li>
    </ul>

    <hr>

    <ul class="firebaseui-idp-list">
      <li class="firebaseui-list-item">
        アカウントをお持ちですか？
      </li>
      <li class="firebaseui-list-item">
        <router-link to="/login">
          既にあるアカウントでログインする
        </router-link>
      </li>
    </ul>
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
      error: null,
      errors: null
    };
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

      // ゲストログインしている場合
      if (firebase.auth().currentUser) {
        var credential = firebase.auth.EmailAuthProvider.credential(this.customer.email, this.customer.password);
        firebase.auth().currentUser.linkWithCredential(credential)
        .then((userCredential) => {
          this.customer.id = userCredential.user.uid
          this.createCustomer()
        })

        .catch((error) => {
          alert(error.message);
        });

        return
      }

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
    // Googleサインアップ
    // ========================================================================
    gSignUp: function() {

      const provider = new firebase.auth.GoogleAuthProvider();

      // ゲストログインしている場合
      if (firebase.auth().currentUser) {
        firebase.auth().currentUser.linkWithPopup(provider)
        .then((userCredential) => {
          this.customer.id = userCredential.user.uid
          this.createCustomer()
        })

        .catch((error) => {
          alert(error.message);
        });

        return
      }

      firebase.auth().signInWithPopup(provider)
      .then((userCredential) => {
        this.customer.id = userCredential.user.uid
        this.customer.name = userCredential.user.displayName
        this.customer.email = userCredential.user.email
        this.createCustomer()
      })

      .catch((error) => {
        this.error = error
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
        this.$router.push('/')
      })

      .catch(error => {
        this.errors = error.response.data.errors
      })
    },
  }
};
</script>
