import Vue from "vue";

export default class apiClass{
    static postImage(image){
        let json = {
            pseudo: "xX_jonh_Xx",
            image: image
        }
        return Vue.http.post("http://localhost:8080/API/upload/send", json)
    }
}