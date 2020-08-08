import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    customer: {},
    cart: []
  },
  mutations: {
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
