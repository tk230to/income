<template>
  <div class="signup">
    <table>
      <tr>
        <th>メールアドレス：</th>
      </tr>
      <tr>
        <td>
          <input type="email" v-model="mailaddress" />
        </td>
      </tr>
      <tr>
        <th>パスワード：</th>
      </tr>
      <tr>
        <td>
          <input type="password" v-model="password" />
        </td>
      </tr>
    </table>

    <b-button variant="outline-primary" @click="signUp">新しいアカウントを作成</b-button>
  </div>
</template>

<script>
import firebase from "firebase/app";

export default {
  data: function() {
    return {
      mailaddress: "",
      password: ""
    };
  },
  methods: {
    signUp: function() {
      firebase
        .auth()
        .createUserWithEmailAndPassword(this.mailaddress, this.password)
        .then
        // 成功時の処理
        ()
        .catch(function(error) {
          alert(error.message);
        });
    }
  }
};
</script>
