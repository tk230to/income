import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({

  // **************************************************************************
  // * データ
  // **************************************************************************
  state: {

    // 顧客
    customer: {},
  },

  // **************************************************************************
  // * mutations
  // **************************************************************************
  mutations: {

    setCustomer(state, customer) {
      state.customer = customer
    },

    getCustomer(state) {
      return state.customer
    },
  },

  // **************************************************************************
  // * actions
  // **************************************************************************
  actions: {

    setCustomer({commit}, customer) {
      commit('setCustomer', customer)
    },

    getCustomer({commit}) {
      commit('getCustomer')
    }
  },

  modules: {
  }
})
