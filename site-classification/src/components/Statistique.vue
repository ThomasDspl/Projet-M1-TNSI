<template>
    <div class="container">
        <h1>STATISTIQUES</h1>
        <div class="underTitle">
            <p>Les données ci-dessous représentent les statistiques globales du site</p>
            <p>À ce jour, le site a analysé :</p>
            <p class="image">{{ stats.nb_image_analyser }} images</p>
            <p>Parmis ces images, nous avons détecté :</p>
            <ul>
                <li>{{ stats.class.nb_0 }} bouteilles en plastique</li>
                <li>{{ stats.class.nb_1 }} sacs en plastique</li>
                <li>{{ stats.class.nb_2 }} canettes</li>
            </ul>
        </div>
    </div>
</template>

<script>
import apiClass from "../API/index"
export default {
    name: 'Statistique',
    data() {
        return {
            stats: {
                nb_image_analyser: '',
                class: {
                    nb_0: '',
                    nb_1: '',
                    nb_2: ''
                }
            }
        }
    },
    created() {
        apiClass.getStatistics().then(
            (response) => {
                this.stats = response.body
            }
        ).catch(
            (error) => {
                console.error('Erreur lors de la récupération des statistiques', error);
            }
        )
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

.image, li {
    font-weight: bold;
}
</style>