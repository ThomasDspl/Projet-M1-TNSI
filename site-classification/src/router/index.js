import Vue from 'vue';
import VueRouter from 'vue-router';
import Home from '../components/Home.vue';
import Login from '../components/Login.vue';
import Upload from '../components/Upload.vue'
import Classement from '../components/Classement.vue'
import Statistique from '../components/Statistique.vue';
import VueResource from "vue-resource";

Vue.use(VueRouter);
Vue.use(VueResource);

const routes = [
    {
      path: '/',
      name: 'Home',
      component: Home,
      
    },
    {
      path: '/stats',
      name: 'Statistique',
      component: Statistique
    },
    {
      path: '/upload',
      name: 'Upload',
      component: Upload
    },
    {
      path: '/classement',
      name: 'Classement',
      component: Classement
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '*',
      redirect: '/'
    }
  ];
  
  const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
  });
  
  export default router;