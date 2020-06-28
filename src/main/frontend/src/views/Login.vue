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

    <b-button variant="outline-primary" @click="login">ログイン</b-button>
    <b-button variant="outline-primary" @click="glogin">Googleログイン</b-button>
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
    login: function() {
      firebase
        .auth()
        .signInWithEmailAndPassword(this.mailaddress, this.password)
        .then(user => {
          console.log(user.user.email);
          this.$router.push("/");
        })
        .catch(function(error) {
          alert(error.message);
        });
    },
    glogin: function() {
      const provider = new firebase.auth.GoogleAuthProvider();
      firebase
        .auth()
        .signInWithPopup(provider)
        .then(result => {
          console.log(result.user);
          this.$router.push("/");
        })
        .catch(error => {
          alert(error.message);
        });
    }
  }
};
</script>
