<template>
  <div>
    <header>
      <div class="indigo p-3">
        <span class="h2">ロゴ</span>

        <section class="float-right">
          <span class="h2">画像</span>
        </section>
      </div>

      <b-navbar toggleable="lg" type="dark" class="indigo" :class="{'fixed-top': scrollY > 100}">
        <b-navbar-brand to="/">ホーム{{this.getLoginUser}}</b-navbar-brand>

        <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

        <b-collapse id="nav-collapse" is-nav>
          <b-navbar-nav>
            <b-nav-item active-class="active" to="/about">初めての方へ</b-nav-item>
            <b-nav-item active-class="active" to="/rental">レンタルする</b-nav-item>
            <b-nav-item active-class="active" to="/buy">購入する</b-nav-item>
          </b-navbar-nav>

          <b-navbar-nav class="ml-auto">
            <b-nav-item v-if="!this.getLoginUser" href="/signup">
              <i class="fas fa-user-plus"></i>新規登録
            </b-nav-item>
            <b-nav-item v-if="!this.getLoginUser" href="/login">
              <i class="fas fa-sign-in-alt"></i> ログイン
            </b-nav-item>
            <b-nav-item v-else href="/login" @click="logoff">
              <i class="fas fa-sign-out-alt"></i> ログオフ
            </b-nav-item>
            <b-nav-item active-class="active" to="/mypage">
              <i class="fas fa-user-cog"></i> マイページ
            </b-nav-item>
            <b-nav-item active-class="active" to="/cart">
              <i class="fas fa-shopping-cart"></i>
              <span class="badge badge-danger rounded-circle ml-1">9</span>
            </b-nav-item>
          </b-navbar-nav>
        </b-collapse>
      </b-navbar>
    </header>

    <main>
      <router-view />
    </main>

    <footer class="indigo">
      <div class="container">
        <div class="row d-flex justify-content-between">
          <div>
            <router-link class="text-white p-3" to="/company">会社紹介</router-link>
            <router-link class="text-white p-3" to="/company">プライバシーポリシー</router-link>
          </div>
          <div>© 2020 Copyright income.com</div>
        </div>
      </div>
    </footer>
  </div>
</template>

<script>
import firebase from "firebase/app";
import "./assets/main.css";

export default {
  // **************************************************************************
  // * 初期処理
  // **************************************************************************
  mounted() {
    window.addEventListener("scroll", this.onScroll);
  },

  // **************************************************************************
  // * 終了処理
  // **************************************************************************
  destroyed() {
    window.removeEventListener("scroll", this.onScroll);
  },

  // **************************************************************************
  // * メソッド
  // **************************************************************************
  methods: {
    onScroll() {
      this.scrollY = window.scrollY;
    },

    // ========================================================================
    // ログオフ
    // ========================================================================
    logoff: function() {
      firebase.auth().signOut();
    }
  },

  // **************************************************************************
  // * データ
  // **************************************************************************
  data: function() {
    return {
      user: "",
      scrollY: 0
    };
  },

  // **************************************************************************
  // * 算出プロパティ
  // **************************************************************************
  computed: {
    getLoginUser: function() {
      firebase.auth().onAuthStateChanged(user => {
        this.user = user ? user.displayName : "";
      });
      return this.user;
    }
  }
};
</script>
