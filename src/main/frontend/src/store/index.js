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
  },

  // **************************************************************************
  // * getter
  // **************************************************************************
  getters: {
    customer: state => {
      return state.customer
    }
  },
})
