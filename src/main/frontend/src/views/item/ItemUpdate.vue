<template>
<div class="container">
  <ItemBase title="商品更新画面" :item="item" :errors="errors"></ItemBase>

  <div class="row">
    <div class="col-sm-6">
      <router-link class="btn btn-primary" to="/item">戻る</router-link>
    </div>
    <div class="col-sm-6 text-right">
      <button class="btn btn-primary" @click="updateItem()">更新</button>
    </div>
  </div>
</div>
</template>

<script>
import axios from 'axios'
import ItemBase from './ItemBase'

export default {

    // **************************************************************************
    // * 表示前処理
    // **************************************************************************
    mounted: function() {
      this.getItem()
    },

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
    // 商品取得
    // ========================================================================
    getItem: async function() {

      // HTTPリクエスト送信
      let item
      await axios.get('/api/open/items/' + this.$route.params.id)
      .then(function (response) {
        item = response.data
      })

      // 画像をBase64デコード
      item.image = await this.base64DecodeAsBlob(item.image, item.imageType)

      this.item = item
    },

    // ========================================================================
    // 商品更新
    // ========================================================================
    updateItem: async function() {

      this.item.image = await this.base64Encode(this.item.image)
      await axios.put('/api/open/items/' + this.item.id, this.item)
      .then(response => {
        console.log(response)
        this.$router.push('/item')
      })

      .catch(error => {
        this.errors = error.response.data.errors
      })
    },
  },
};
</script>
