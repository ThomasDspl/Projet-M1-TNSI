<template>
  <div>
      <h1>UPLOAD</h1>
      <div v-if='loginStatus'>
        <p>Upload un truc</p>
      </div>
      <div v-else>
        <p>Désoler, vous devez être connecter pour accéder à cette fonctionnalité.</p>
      </div>
    </div>
</template>

<script>
import {mapGetters} from 'vuex'
export default {
    name: 'Upload',
    data () {
      return {

      }
    },
    computed: {
      ...mapGetters( {
          loginStatus: 'getLoginStatus'
      })
    }
}
</script>

<style>

</style>