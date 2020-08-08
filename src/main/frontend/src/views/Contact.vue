<template>
  <div class="container">
    <div class="row">
      <div class="col">
        <h1>問合せ</h1>
      </div>
    </div>

    <div class="form-group row">
      <label for="mail">メールアドレス</label>
      <input type="text" id="mail" class="form-control" />
      <label for="question">問合せ内容</label>
      <textarea id="question" class="form-control" />
    </div>

    <form action="/validate/recaptcha" method="post">
      <vue-recaptcha sitekey="6LdpQrYZAAAAAIHuGu-9WrE4sAm-1JVXJJDjJfyJ" :loadRecaptchaScript="true" @verify="onVerify" @expired="onExpired"></vue-recaptcha>
      <input type="submit" value="検証" />
    </form>

    <div class="row">
      <div class="col">
        <button class="btn btn-primary d-block mx-auto m-2 px-3 py-2" :disabled="!token">送信</button>
      </div>
    </div>
  </div>
</template>

<script>
import VueRecaptcha from 'vue-recaptcha';
export default {
  components: {
    VueRecaptcha
  },
  data: function() {
    return {
      token: ""
    };
  },
  methods: {
    onVerify: function (response) {
      this.token = response
    },
    onExpired: function () {
      this.token = ""
    },
  }
};
</script>