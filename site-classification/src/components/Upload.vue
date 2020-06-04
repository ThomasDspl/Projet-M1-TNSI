<template>
  <div>
      <h1>UPLOAD</h1>
      <div v-if='loginStatus'>
        <p>Upload un truc</p>
        <b-form @submit.prevent="sendImage()">
          <input 
          type="file"
          accept="image/*"
          ref="file"
          @change="handlerFileUpload"
        >
        <b-button type="submit" variant="primary">Submit</b-button>
        </b-form>
        <img id='img'>
        <text id='b64' style="visibility:hidden;"></text>
      </div>
      <div v-else>
        <p>Désoler, vous devez être connecter pour accéder à cette fonctionnalité.</p>
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
          apiClass.postImage(document.getElementById("b64").value).then((response) => {
            console.log("OK", response);
          }).catch((error) => {
            console.error("ERROR", error);
          })
      }
        
    } 
}
</script>

<style>

</style>