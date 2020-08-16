import Vue from 'vue'
import VueRouter from 'vue-router'
import firebase from 'firebase/app'

import Home from '@/views/Home.vue'
import About from '@/views/About.vue'
import Buy from '@/views/Buy.vue'
import Cart from '@/views/Cart.vue'
import Login from '@/views/Login.vue'
import MyPage from '@/views/MyPage.vue'
import Rental from '@/views/Rental.vue'
import SignUp from '@/views/SignUp.vue'
import Contact from '@/views/Contact.vue'
import Company from '@/views/Company.vue'
import Privacy from '@/views/Privacy.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/about',
    name: 'About',
    component: About
  },
  {
    path: '/buy',
    name: 'Buy',
    component: Buy
  },
  {
    path: '/cart',
    name: 'Cart',
    component: Cart
  },
  {
    path: '/signup',
    name: 'SignUp',
    component: SignUp
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/mypage',
    name: 'MyPage',
    component: MyPage,
    meta: { requiresAuth: true }
  },
  {
    path: '/rental',
    name: 'Rental',
    component: Rental
  },
  {
    path: '/contact',
    name: 'Contact',
    component: Contact
  },
  {
    path: '/company',
    name: 'Company',
    component: Company
  },
  {
    path: '/privacy',
    name: 'Privacy',
    component: Privacy
  },
]

const router = new VueRouter({
  mode: 'hash',
  base: process.env.BASE_URL,
  routes
})

router.beforeEach((to, from, next) => {
  const requiresAuth = to.matched.some(record => record.meta.requiresAuth)
  if (requiresAuth) {
    firebase.auth().onAuthStateChanged(function (user) {
      if (user) {
        next()
      } else {
        next({
          path: '/login',
          query: { redirect: to.fullPath }
        })
      }
    })
  } else {
    next()
  }
})

export default router
