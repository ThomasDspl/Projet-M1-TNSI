<template>
  <div class="container">
    <h1>Bienvenue sur DetriClass</h1>
    <div class="underTitle">
      <p>Ce site permet de voir des statistiques sur le nombre de déchets sur les bords de routes</p>
      <p>Il permet aussi d'envoyer vos propres photos pour analyse</p>
      <p>Un classement peut aussi être consulté</p>
    </div>
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
.container {
    font-size: x-large;
}

.underTitle {
    position: absolute;
    top: 50%;
    left: 50%;
    margin-right: -50%;
    transform: translate(-50%, -50%);
}

h1 {
    position: absolute;
    top: 15%;
    left: 50%;
    margin-right: -50%;
    transform: translate(-50%, -50%);
}

</style>
