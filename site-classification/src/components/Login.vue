<template>
  <div>
    <b-alert v-model="showDismissibleAlert" variant="danger" dismissible>
      {{ alertMessage }}
    </b-alert>

    <b-form>
      <b-form-group
        id="input-group-1"
        label="Adresse e-mail :"
        label-for="input-1"
      >
        <b-form-input
          id="input-1"
          v-model="form.email"
          type="email"
          required
          placeholder="Indiquez votre adresse e-mail"
        ></b-form-input>
      </b-form-group>

      <b-form-group id="input-group-2" label="Mot de passe :" label-for="input-2">
        <b-form-input
          id="input-2"
          v-model="form.password"
          type="password"
          required
          placeholder="Indiquez votre mot de passe"
        ></b-form-input>
      </b-form-group>

      <b-form-group id="input-group-3" label="Nom :" label-for="input-3" v-if="show">
        <b-form-input
          id="input-3"
          v-model="form.name"
          :required="show"
        ></b-form-input>
      </b-form-group>

      <b-form-group id="input-group-4" label="Prénom :" label-for="input-4" v-if="show">
        <b-form-input
          id="input-4"
          v-model="form.surname"
          :required="show"
        ></b-form-input>
      </b-form-group>

      <b-form-group id="input-group-5" label="Pseudo :" label-for="input-5" v-if="show">
        <b-form-input
          id="input-5"
          v-model="form.pseudo"
          :required="show"
        ></b-form-input>
      </b-form-group>

      <b-button @click="onSubmit" variant="primary">Se connecter</b-button>
      <b-button @click="onSign" variant="danger">S'inscrire</b-button>
    </b-form>
  </div>
</template>

<script>
import apiClass from "../API/index"
export default {
    name: 'Login',
    data(){
        return {
            form: {
                email: '',
                password: '',
                name: '',
                surname: '',
                pseudo: ''
            },
            show: false,
            showDismissibleAlert: false,
            alertMessage: ''
        }
    },
    methods: {
        onSubmit() {
            if (this.show) {
                this.show = false;
            }
            else if(this.isFormValid) {
                apiClass.login(JSON.stringify({ email: this.form.email, password: this.form.password })).then(
                    (response) => {
                        if (response.status === 200) {
                            this.$session.start()
                            this.$session.set('email', response.body.email)
                            this.$session.set('name', response.body.name)
                            this.$session.set('surname', response.body.surname)
                            this.$session.set('score', response.body.score)
                            this.$session.set('pseudo', response.body.pseudo)
                            this.$store.commit("setLoginStatus", true)
                            this.$router.push('/')
                        }
                    }
                ).catch(
                    (error) => {
                        this.alertMessage = 'Email ou mot de passe incorrect';
                        this.showDismissibleAlert = true;
                        console.error('Erreur lors de l\'authentification', error);
                    }
                )
            }
            else {
              this.alertMessage = 'Le formulaire est invalide';
              this.showDismissibleAlert = true;
            }   
        },
        onSign: function() {
            if (!this.show) {
                this.show = true
            }
            else {
                if (this.isFormValidS) {
                    apiClass.newUser(JSON.stringify(this.form)).then(
                        (response) => {
                            if (response.status === 200) {
                                this.onReset();
                                this.alertMessage = 'Inscription réussie';
                                this.showDismissibleAlert = true;
                            }
                        }
                    ).catch(
                        (error) => {
                            console.error('Erreur lors de l\'inscription', error);
                            this.alertMessage = 'Erreur lors de l\'inscription';
                            this.showDismissibleAlert = true;
                        }
                    );
                }
                else {
                    this.alertMessage = 'Le formulaire est invalide';
                    this.showDismissibleAlert = true;
                }
            }
        },
        onReset() {
        this.form.email = ''
        this.form.password = ''
        this.form.name = ''
        this.form.surname = ''
        this.form.pseudo = ''
        this.show = false
      }
    },
    computed: {
        isFormValid: function(){
            return this.form.email != '' && this.form.mdp != ''
        },
        isFormValidS: function() {
            return this.form.email != '' && this.form.mdp != '' && this.form.pseudo != '' && this.form.name != '' && this.form.surname != ''
        } 
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
      font-size: x-large;
      margin-left: auto;
      margin-right: auto;
      width: 70%;
    }
</style>