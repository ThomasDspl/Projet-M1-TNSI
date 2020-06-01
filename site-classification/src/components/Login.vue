<template>
  <div>
      <h1>Login</h1>
        <b-form @submit.prevent="onSubmit()">
          <b-form-group
            id="input-group-1"
            label="Email: "
            label-for="input-1"
          >
              <b-form-input
                id="input-1"
                v-model="email"
                type="email"
                required
                placeholder="example@domain.com"
              >
              </b-form-input>
          </b-form-group>
            <b-form-group
                id="input-group-2"
                label="Mot de passe: "
                label-for="input-2"
            >
                <b-form-input
                    id="input-2"
                    v-model="mdp"
                    type="password"
                    required
                    placeholder="Mot de passe"
                >
                </b-form-input>
            </b-form-group>
            <b-button class="mr-1" variant="primary">S'inscrire</b-button>
            <b-button type="submit" variant="primary">Se connecter</b-button>
        </b-form>
  </div>
</template>

<script>
export default {
    name: 'Login',
    data(){
        return {
            email: null,
            mdp: null
        }
    },
    methods: {
        onSubmit: function(){
            if(this.isFormValid){
                console.log("ICI !");
                this.$session.start()
                this.$session.set('email', this.email)
                this.$session.set('mdp', this.mdp)
                this.$store.commit("setLoginStatus", true)
                this.$router.push('/')
            }else {
                console.log("WHYYYYY");
                
            }
        }
    },
    computed: {
        isFormValid: function(){
            return this.email != '' && this.mdp != ''
        },
        
    },
    beforeCreate: function() {
        if(this.$session.exists()){
            this.$router.push('/')
        }
    }
}
</script>

<style scoped>
    div {
        margin-left: auto;
        margin-right: auto;
        width: 70%;

    }
</style>