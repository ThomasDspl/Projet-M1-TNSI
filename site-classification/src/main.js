import Vue from 'vue'
import App from './App.vue'
import VueSession from 'vue-session'
import BootstrapVue from 'bootstrap-vue/dist/bootstrap-vue.esm'
import router from './router';
import store from './store'

// Import the styles directly. (Or you could add them via script tags.)
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap-vue/dist/bootstrap-vue.css';

Vue.config.productionTip = false

Vue.use(VueSession)
Vue.use(BootstrapVue)

new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app')
