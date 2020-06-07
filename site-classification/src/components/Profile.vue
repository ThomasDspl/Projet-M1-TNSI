<template>
    <div class="container">
        <h1>PROFILE</h1>
        <div class="underTitle">
            <p>Nom : {{ name }}</p>
            <p>Prénom : {{ surname }}</p>
            <p>Score : {{ score }}</p>
            <p>E-mail : {{ email }}</p>
            <p>Pseudo : {{ pseudo }}</p>
        </div>
    </div>
</template>

<script>
export default {
    name: 'Profile',
    data() {
        return {
            name: '',
            surname: '',
            email: '',
            score: 0,
            pseudo: ''
        }
    },
    created() {
        this.name = this.$session.get('name')
        this.surname = this.$session.get('surname')
        this.email = this.$session.get('email')
        this.score = this.$session.get('score')
        this.pseudo = this.$session.get('pseudo')
    }
}
</script>

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