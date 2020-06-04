import Vue from "vue";

export default class apiClass{
    static postImage(image){
        return Vue.http.post("http://localhost:8080/API/upload/send", image)
    }
}