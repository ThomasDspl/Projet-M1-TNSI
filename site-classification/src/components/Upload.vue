<template>
  <div class="container">
    <b-alert v-model="showDismissibleAlert" variant="danger" dismissible>
      {{ alertMessage }}
    </b-alert>
      <h1>UPLOAD</h1>
      <div v-if='loginStatus' class="underTitle">
        <p>Ici, vous pouvez envoyer une image contenant une ordure qui sera analysé afin de déterminer sa nature, et augmentera votre score</p>
        <br />
        <b-form @submit.prevent="sendImage()">
          <input 
          type="file"
          accept="image/*"
          ref="file"
          @change="handlerFileUpload"
        >
        <b-button type="submit" variant="primary">Envoyer l'image</b-button>
        </b-form>
        <br />
        <img id='img'>
        <text id='b64' style="visibility:hidden;"></text>
        <br />
        <br />
        <b-overlay :show="show" rounded="sm">
        <p v-if="img">Selon notre analyse, votre image correspond à {{ img }}, ce choix correspond au probabilités suivantes :</p>
        <ul v-if="img">
          <li>Bouteille en plastique : {{ imgData.proba.c_0 * 100 }}%</li>
          <li>Sac en plastique : {{ imgData.proba.c_1 * 100 }}%</li>
          <li>Canette : {{ imgData.proba.c_2 * 100 }}%</li>
        </ul>
        </b-overlay>
      </div>
      <div v-else class="underTitle">
        <p>Vous devez être connecté pour accéder à cette fonctionnalité.</p>
      </div>
    </div>
</template>

<script>
import {mapGetters} from 'vuex'
import apiClass from "../API/index"
export default {
    name: 'Upload',
    data () {
      return {
        imgData: {},
        img: '',
        showDismissibleAlert: false,
        alertMessage: '',
        show: false
      }
    },
    computed: {
      ...mapGetters( {
          loginStatus: 'getLoginStatus'
      })
    },
    methods: {
      handlerFileUpload(){
       /* var image = this.$refs.file.files[0]
        var img = document.createElement("img")
        var c = document.createElement('canvas')
        img.src = image
        console.log(img);
        console.log(image);
        c.width = img.width;
        c.height = img.height;
        var ctx = c.getContext('2d')
        ctx.drawImage(img, 0, 0)
        this.imageB64 = c.toDataURL()*/
        if (this.$refs.file && this.$refs.file.files[0]) {
          var FR= new FileReader();
          FR.addEventListener("load", (e) =>{
            document.getElementById("img").src = e.target.result;
            document.getElementById("b64").value = e.target.result;
          }); 
          FR.readAsDataURL( this.$refs.file.files[0]);
        }
      },
      sendImage: function() {
          this.show = true;
          apiClass.postImage(document.getElementById("b64").value, this.$session.get('pseudo')).then((response) => {
            this.imgData = response.body;
            this.image();
            this.show = false;
          }).catch((error) => {
            this.show = false;
            this.alertMessage = 'Erreur lors de l\'envoi de l\'image';
            this.showDismissibleAlert = true;
            console.error("ERROR", error);
          })
      },
      image() {
        if (this.imgData.class === '0') {
          this.img = 'une bouteille en plastique'
        }
        else if (this.imgData.class === '1') {
          this.img = 'un sac en plastique'
        }
        else {
          this.img = 'une canette'
        }
      }
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

img {
  height: 150px;
  width: 200px;
}
</style>