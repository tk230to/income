<template>
<div class="container">
  <ItemBase title="商品登録画面" :item="item" :errors="errors"></ItemBase>

  <div class="row">
    <div class="col-sm-6">
      <router-link class="btn btn-primary" to="/item">戻る</router-link>
    </div>
    <div class="col-sm-6 text-right">
      <button class="btn btn-primary" @click="createItem()">登録</button>
    </div>
  </div>
</div>
</template>

<script>
import axios from 'axios'
import ItemBase from './ItemBase'

export default {

  // **************************************************************************
  // * データ
  // **************************************************************************
  data: function() {
    return {
      item: {},
      errors: null
    }
  },

  // **************************************************************************
  // * コンポーネント
  // **************************************************************************
  components: {
    ItemBase
  },

  // **************************************************************************
  // * メソッド
  // **************************************************************************
  methods: {

    // ========================================================================
    // 商品登録
    // ========================================================================
    createItem: async function() {

      // HTTPリクエスト送信
      await axios.post('/api/open/items/', this.item)
      .then(response => {
        this.item = response.data
        this.$router.push('/item')
      })

      .catch(error => {
        this.errors = error.response.data.errors
      })
    },
  }
};
</script>
