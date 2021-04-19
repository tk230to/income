<template>
  <div>
    <h1>{{ title }}</h1>

    <div class="form-group row">
      <label class="col-sm-2 col-form-label">名前 <span class="badge badge-danger">必須</span></label>
      <div class="col-sm-10">
        <input type="text" class="form-control" :class="{ 'is-invalid' : getErrorMessage('name') }" v-model="item.name">
        <div :class="{ 'invalid-feedback' : getErrorMessage('name') }">{{getErrorMessage('name')}}</div>
      </div>
    </div>
    <div class="form-group row">
      <label class="col-sm-2 col-form-label">金額 <span class="badge badge-danger">必須</span></label>
      <div class="col-sm-10">
        <input type="number" class="form-control" :class="{ 'is-invalid' : getErrorMessage('price') }" v-model="item.price">
        <div :class="{ 'invalid-feedback' : getErrorMessage('price') }">{{getErrorMessage('price')}}</div>
      </div>
    </div>
    <div class="form-group row">
      <label class="col-sm-2 col-form-label">画像 <span class="badge badge-danger">必須</span></label>
      <div class="col-sm-10 bg-light" @drop.prevent="dropImage($event)">
        <input type="file" class="w-100 position-absolute" style="opacity: 0" @change="changeImage($event)" :class="{ 'is-invalid' : getErrorMessage('image') }">

        <p>クリックでファイル選択</p>
        <p>または画像をドラッグ＆ドロップ</p>
        <img v-show="getImageSrc()" :src="getImageSrc()" class="w-25">
        <p v-show="file.name"> {{file.name}} </p>

        <div :class="{ 'invalid-feedback' : getErrorMessage('image') }">{{getErrorMessage('image')}}</div>
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
        file: {
          name: null
        },
      }
    },

  // **************************************************************************
  // * 引数
  // **************************************************************************
  props: {
    title: String,
    item: Object,
    errors: Array
  },

  // **************************************************************************
  // * メソッド
  // **************************************************************************
  methods: {

    // ========================================================================
    // エラーメッセージ取得
    // ========================================================================
    getErrorMessage: function(fieldName) {

      // エラーが無い場合
      if (!this.errors) {
        return ""
      }

      for (let error of this.errors) {
        if (error.field === fieldName) {
          return error.defaultMessage
        }
      }
    },

    // ========================================================================
    // 画像ファイルドラッグ＆ドロップ
    // ========================================================================
    dropImage: function(event) {
      this.loadImage(event.dataTransfer.files);
    },

    // ========================================================================
    // 画像ファイル変更
    // ========================================================================
    changeImage: function(event) {
        this.loadImage(event.target.files);
    },

    // ========================================================================
    // 画像ファイルプレビュー表示
    // ========================================================================
    loadImage: function(files) {

      // 1ファイルのみ選択可能
      if (files.length !== 1 || files[0].type.indexOf("image") !== 0) {
        return;
      }

      const file = files[0];
      this.file.name = file.name;
      this.item.image = file
      this.item.imageType = file.type
    },

    // ========================================================================
    // 画像ファイルソース取得
    // ========================================================================
    getImageSrc: function() {

      // 未選択 or 画像のBase64デコードが未完了の場合
      if (!this.item.image || !this.item.image.type) {
        return ""
      }

      return URL.createObjectURL(this.item.image)
    }
  }
};
</script>
