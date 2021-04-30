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
      <button @click="update()" class="btn btn-primary d-block mx-auto m-2 px-3 py-2">更新</button>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {

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
        console.log(response)
        this.$router.push('/')
      })

      .catch(error => {
        this.errors = error.response.data.errors
      })
    },
  }
};
</script>