<template>
  <div class="container container-fluid">
    <div class="row">
      <div class="col">
        <h1>マイページ</h1>
      </div>
    </div>

    <div class="form-group row">
      <div class="form-inline">
        <label>ID：</label>
        <span>{{this.customer.id}}</span>
      </div>
    </div>
    <div class="form-group row">
      <div class="form-inline">
        <label>名前：</label>
        <input class="form-control" type="text" v-model="customer.name" />
      </div>
    </div>
    <div class="row">
      <button @click="create()" class="btn btn-primary d-block mx-auto m-2 px-3 py-2">登録</button>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  // **************************************************************************
  // * データ
  // **************************************************************************
  data: function() {
    return {
      customer: JSON.parse(JSON.stringify(this.$store.state.customer)),
    }
  },

  // **************************************************************************
  // * メソッド
  // **************************************************************************
  methods: {
    // ========================================================================
    // 登録
    // ========================================================================
    create: async function () {
      await axios.post('/api/open/customers', this.customer)
    },
  }
};
</script>