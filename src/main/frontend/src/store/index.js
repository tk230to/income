import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    customer: {},
    cart: [],
    token: ""
  },
  mutations: {
    setToken(state, payload) {
      state.token = payload
    },
    setCustomer(state, payload) {
      state.customer = payload
    },
    getCustomer(state) {
      return state.customer
    },
    addCart(state, payload) {
      state.cart.push(payload);
    }
  },
  actions: {
    setToken({commit}, payload) {
      commit('setToken', payload)
    },
    setCustomer({commit}, payload) {
      commit('setCustomer', payload)
    },
    getCustomer({commit}) {
      commit('getCustomer')
    }
  },
  modules: {
  }
})
