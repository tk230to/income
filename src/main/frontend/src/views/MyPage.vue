<template>
  <div class="container">
    <h1>マイページ</h1>

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

    <div class="row">
      <div class="col-sm-6">
        <button class="btn btn-primary" @click="cancel()">解約</button>
      </div>
      <div class="col-sm-6 text-right">
        <button class="btn btn-primary" @click="update()">更新</button>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import firebase from "firebase/app";

export default {

  // **************************************************************************
  // * データ
  // **************************************************************************
  data: function() {
    return {
      errors: null
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
    // 更新
    // ========================================================================
    update: async function () {
      await axios.put('/api/open/customers/' + this.customer.id, this.customer)
      .then(response => {
        this.customer = response.data
        this.$router.push('/')
      })

      .catch(error => {
        this.errors = error.response.data.errors
      })
    },

    // ========================================================================
    // 解約
    // ========================================================================
    cancel: async function () {

      firebase.auth().currentUser.delete().then(() => {
        // User deleted.
      }).catch(error => {
        console.log(error)
      });
    },
  }
};
</script>