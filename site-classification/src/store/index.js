import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    loginStatus: false
  },
  mutations: {
    setLoginStatus(state, status) {
        state.loginStatus = status;
      }
  },
  getters: {
    getLoginStatus(state) {
      return state.loginStatus;
    }
  }
});
