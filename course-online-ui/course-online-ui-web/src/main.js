import Vue from 'vue'
import App from './App.vue'
import router from "./router";
import axios from "axios";

Vue.config.productionTip = false;

Vue.prototype.$ajax = axios;

axios.interceptors.request.use(config => {
    console.log("请求：", config);
    return config;
}, error => {
    console.log(error);
    return Promise.reject(error);
});

//响应拦截器
axios.interceptors.response.use(response => {

}, error => {
    console.log(error);
});

new Vue({
    router,
    render: h => h(App),
}).$mount('#app');
