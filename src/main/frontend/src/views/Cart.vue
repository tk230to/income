<template>
  <div class="container">
    <h1>カート</h1>

    <div class="row">
      <h2>小計: {{ subtotalPrice }}</h2>
    </div>

    <div class="form-group row">
      <div v-for="(cartItem, index) in this.customer.cartItems" class="col-lg-4 col-md-6" :key="index">
        <div class="card" style="width: 18rem;">
          <img :src="getImageSrc(cartItem.item.image)">
          <div class="card-body">
            <h5 class="card-title">{{cartItem.item.name}}</h5>
            <p class="card-text">￥{{cartItem.item.price.toLocaleString()}}</p>
            <div class="form-inline">
              <label>数量</label>
              <input class="form-control" type="number" v-model.number="cartItem.quantity" />
            </div>
            <button @click="deleteItem(index)" class="btn btn-secondary d-block mx-auto m-2 px-3 py-2">削除</button>
          </div>
        </div>
      </div>
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

    subtotalPrice: function() {
      let subtotal = 0
      if (this.customer.cartItems) {
        for (let cartItem of this.customer.cartItems) {
          subtotal = subtotal + cartItem.item.price * cartItem.quantity
        }
      }
      return subtotal
    },
  },

  // **************************************************************************
  // * メソッド
  // **************************************************************************
  methods: {

    // ========================================================================
    // カート商品削除
    // ========================================================================
    deleteItem: async function(index) {
      console.log(JSON.stringify(this.customer.cartItems))
      await axios.delete('/api/open/cartitems/' + this.customer.cartItems[index].id)
      .then(response => {
        console.log(response)
        this.getCurrentCustomer()
      })

      .catch(error => {
        this.errors = error.response.data.errors
      })
    },

    // ========================================================================
    // 画像ファイルソース取得
    // ========================================================================
    getImageSrc: function(image) {

      if (!image || !image.type) {
        return ""
      }

      return URL.createObjectURL(image)
    }
  }
};
</script>
<style>
img {
  width: 17rem;
  height: 17rem;
  object-fit: contain;
}
</style>
