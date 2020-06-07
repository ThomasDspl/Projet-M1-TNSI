<template>
    <div id="head">
        <b-navbar class="nav" fixed='top' toggleable="lg" type="dark" variant="info">
            <b-navbar-brand :to="{path: '/home'}">DetriClass</b-navbar-brand>
             <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
              <b-collapse id="nav-collapse" is-nav>
                <b-navbar-nav>
                    <b-nav-item :to="{path: '/stats'}"> Statistiques</b-nav-item>
                    <b-nav-item :to="{path: '/upload'}">Upload une image</b-nav-item>
                    <b-nav-item :to="{path: '/classement'}">Classement</b-nav-item>
                    <b-nav-item-dropdown v-if='loginStatus' right>
                    <!-- Using 'button-content' slot -->
                        <template v-slot:button-content>
                            <em>Mon Compte</em>
                        </template>
                        <b-dropdown-item :to="{path: '/profile'}">Profile</b-dropdown-item>
                        <b-dropdown-item @click='logOut'>Log Out</b-dropdown-item>
                    </b-nav-item-dropdown>
                    <b-nav-item v-else :to="{path: '/login'}">Login</b-nav-item>
                </b-navbar-nav>
            </b-collapse>
        </b-navbar>
    </div>
</template> 
<script>
import {mapGetters} from 'vuex'
  export default {
  name: 'Header',
  data() {
      return {
      }
  },
  methods: {
      logOut: function() {
          this.$store.commit("setLoginStatus", false)
          this.$session.destroy()
          this.$router.push('Home')
      }
  },
  computed: {
      ...mapGetters( {
          loginStatus: 'getLoginStatus'
      }),
      test(){
          console.log(this.loginStatus);
          return this.loginStatus
      }
  }
}
</script>
<style scoped>
    .nav {
        background-color: rgba(60, 60, 60, 1) ! important;
    }
    
</style>