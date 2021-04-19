<template>
  <div class="container">
    <div class="row">
      <div class="col">
        <h1>商品管理</h1>

        <router-link class="btn btn-primary" to="/item/create">新規登録</router-link>

        <table class="table table-bordered table-hover">
          <thead class="thead-dark">
            <tr>
              <th width="10%">ID</th>
              <th width="80%">名前</th>
              <th width="10%">削除</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(item, index) in this.items" :key="index">
              <td>{{item.id}}</td>
              <td><router-link :to="{name: 'ItemUpdate', params: {id: item.id}}">{{item.name}}</router-link></td>
              <td><a @click="deleteItem(item.id)"><i class="far fa-trash-alt fa-lg" aria-hidden="true"></i></a></td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'

export default {

  // **************************************************************************
  // * 表示前処理
  // **************************************************************************
  mounted: function() {
    this.getItems()
  },

  // **************************************************************************
  // * データ
  // **************************************************************************
  data: function() {
    return {
      items: []
    }
  },

  // **************************************************************************
  // * メソッド
  // **************************************************************************
  methods: {

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
    },

    // ========================================================================
    // 商品削除
    // ========================================================================
    deleteItem: async function(id) {

      await axios.delete('/api/open/items/' + id)
      .then(response => {
        console.log(response)
        this.getItems()
      })

      .catch(error => {
        this.errors = error.response.data.errors
      })
    }
  }
};
</script>
