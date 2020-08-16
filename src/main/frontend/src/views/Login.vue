<template>
  <div class="signup">
    <table>
      <tr>
        <th>メールアドレス：</th>
      </tr>
      <tr>
        <td>
          <input type="email" v-model="email" />
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
import axios from 'axios';
import firebase from "firebase/app";
import { mapActions } from "vuex";

export default {
  data: function() {
    return {
      email: "",
      password: ""
    };
  },
  methods: {
    ...mapActions([
      "setToken",
      "setCustomer",
    ]),
    login: async function() {
      try {
        // メールアドレスとパスワードを使って認証
        await firebase.auth().signInWithEmailAndPassword(this.email, this.password)
        await this.setCurrentUser(firebase.auth().currentUser)

        // 認証後のページに遷移
        this.$router.push('/')
      } catch (e) {
        // 認証エラー、トークン取得エラー時
        console.log(e)
      }
    },

    glogin: async function() {
      try {
        const provider = new firebase.auth.GoogleAuthProvider();
        await firebase.auth().signInWithPopup(provider);
        await this.setCurrentUser(firebase.auth().currentUser)

        // 認証後のページに遷移
        this.$router.push('/')
      } catch (e) {
        // 認証エラー、トークン取得エラー時
        console.log(e)
      }
    },

    setCurrentUser: async function(currentUser) {
      var customer
      console.log(currentUser)
      const token = await firebase.auth().currentUser.getIdToken(true)
      this.setToken(token)
      await axios.get('/api/open/customers/' + currentUser.email)
      .then(function (response) {
        customer = response.data
      })
      this.setCustomer(customer)
    }
  }
};
</script>
