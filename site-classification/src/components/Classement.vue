<template>
  <div>
    <b-table striped hover :items="users" :fields="fields" fixed></b-table>
  </div>
</template>

<script>
  import apiClass from "../API/index"
  export default {
    name: 'Classement',
    data() {
      return {
        fields: [
          {
            key: 'pseudo',
            sortable: true
          },
          {
            key: 'nb_image_analysee',
            label: 'Nombre d\'images analysés',
            sortable: true
          },
          {
            key: 'score',
            sortable: true
          }
        ],
        users: []
      }
    },
    created() {
      apiClass.getClassement().then(
          (response) => {
            this.users = response.body.users;
          }
        ).catch(
          (error) => {
            console.error('Erreur lors de la récupération du classement', error);
          }
        );
    }
  }
</script>