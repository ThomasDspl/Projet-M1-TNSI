import Vue from "vue";
const API_BASE_URL = "http://localhost:8080/";

export default class apiClass{
    static newUser(json) {
        return Vue.http.post(`${API_BASE_URL}API/users/registration`, json);
    }

    static login(json) {
        return Vue.http.post(`${API_BASE_URL}API/users/logging`, json);
    }

    static getClassement() {
        return Vue.http.get(`${API_BASE_URL}API/users/classement`);
    }

    static getStatistics() {
        return Vue.http.get(`${API_BASE_URL}API/users/stats`);
    }

    static postImage(image, pseudo){
        let json = {
            pseudo: pseudo,
            image: image
        }
        return Vue.http.post(`${API_BASE_URL}API/upload/send`, json);
    }
}