<template>
  <div class="container">
    <div class="row">
      <div class="col">
        <h1>カート</h1>
        <h2>小計: {{ subtotalPrice }}</h2>

        <div v-for="(item, index) in this.cart" :key="index">
          <p>{{item.name}}</p>
          <div class="form-inline">
            <label>数量</label>
            <input class="form-control" type="number" v-model="item.count" />
          </div>
          <button @click="deleteItem(index)" class="btn btn-secondary">削除</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  // **************************************************************************
  // * データ
  // **************************************************************************
  data: function() {
    return {
      cart: this.$store.state.cart,
    }
  },

  // **************************************************************************
  // * 算出プロパティ
  // **************************************************************************
  computed: {
    subtotalPrice: function() {
      let subtotal = 0
      for (let item of this.cart) {
        subtotal = subtotal + item.price * item.count
      }
      return subtotal
    },
  },

  // **************************************************************************
  // * メソッド
  // **************************************************************************
  methods: {
    deleteItem: function(index) {
      this.cart.splice(index, 1)
    }
  }
};
</script>
