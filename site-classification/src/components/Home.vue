<template>
  <div class="hello">
    <h1>Bienvenue sur DetriClass (version alpha)</h1>
    <p>Ce site permet de voir des statistiques sur le nombre de déchets sur les bords de routes.</p>
    <p>il permet aussi d'envoyer vos propres photos pour analyse.</p>
    <p>Un classement peut aussi être consulter.</p>
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  props: {
    msg: String
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
