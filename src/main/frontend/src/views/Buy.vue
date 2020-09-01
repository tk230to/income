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
export default {
  // **************************************************************************
  // * データ
  // **************************************************************************
  data: function() {
    return {
      items: [
        {
          name: "GDR4800",
          maker: "MOTOROLA",
          imgSrc:
            "https://www.incom-rental.com/incom/manual/images/manual_incom_img01.png",
          price: 1000,
          text:
            "人気No.1の定番モデル。「使いやすさ」を徹底的に追求した、弊社おすすめのハイパワー無線機。",
          count: 1
        },
        {
          name: "IC-D60",
          maker: "ICOM",
          imgSrc:
            "https://www.incom-rental.com/incom/incom/images/incom_img02.png",
          price: 1000,
          text: "根強い人気を誇る機種。あらゆる現場でのご利用が可能です。",
          count: 1
        },
        {
          name: "IP500H",
          maker: "ICOM",
          imgSrc:
            "https://www.incom-rental.com/incom/ip/images/incom_img01.png",
          price: 1000,
          text: "auの4G LTEを使用することで日本全国で使用することを可能。",
          count: 1
        }
      ]
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
    }
  }
};
</script>