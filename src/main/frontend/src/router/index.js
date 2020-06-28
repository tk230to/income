import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '@/views/Home.vue'
import About from '@/views/About.vue'
import Buy from '@/views/Buy.vue'
import Cart from '@/views/Cart.vue'
import Login from '@/views/Login.vue'
import MyPage from '@/views/MyPage.vue'
import Rental from '@/views/Rental.vue'
import SignUp from '@/views/SignUp.vue'

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
    component: MyPage
  },
  {
    path: '/rental',
    name: 'Rental',
    component: Rental
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
