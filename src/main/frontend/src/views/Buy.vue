<template>
  <div class="container">
    <div class="row">
      <div class="col">
        <h1>購入</h1>
      </div>
    </div>

    <div class="form-group row">
      <div v-for="(item, index) in items" class="col-lg-4 col-md-6" :key="index">
        <h2 class="text-center">{{item.name}}</h2>
        <div class="h-25">
          <img class="d-block mx-auto h-100 w-auto" style="object-fit: cover;" :src="item.imgSrc" />
        </div>
        <p class="text-center text-secondary">{{item.maker}}</p>
        <div class="text-wrap" style="height: 15%">{{item.text}}</div>
        <p>￥{{item.price.toLocaleString()}}</p>
        <div class="form-inline">
          <label>数量</label>
          <input class="form-control" type="number" v-model="item.count" />
        </div>
        <button @click="addCart(item)" class="btn btn-primary d-block mx-auto m-2 px-3 py-2">カートに入れる</button>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  mounted: function() {
    this.getItems()
  },

  // **************************************************************************
  // * データ
  // **************************************************************************
  data: function() {
    return {
      items: []
    };
  },

  // **************************************************************************
  // * メソッド
  // **************************************************************************
  methods: {
    // ========================================================================
    // カートに追加処理
    // ========================================================================
    addCart(item) {
      this.$store.commit("addCart", JSON.parse(JSON.stringify(item)));
    },
    
    // ========================================================================
    // 商品取得
    // ========================================================================
    getItems: async function() {
      let items
      await axios.get('/api/open/items/')
      .then(function (response) {
        items = response.data
      })
      this.items = items
    }
  }
};
</script>