import Vue from 'vue'
import App from './App.vue'
import router from "./router";
import axios from "axios";

import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import locale from "element-ui/src/locale/lang/zh-CN";


Vue.use(ElementUI, {locale});

Vue.config.productionTip = false;

Vue.prototype.$store = axios;

axios.interceptors.request.use(config => {
    console.log("请求：", config);
    return config;
}, error => {
    console.log(error);
    return Promise.reject(error);
});

//响应拦截器
axios.interceptors.response.use(response => {
    console.log("响应：", response);
    return response;
}, error => {
    console.log(error);
});

new Vue({
    router,
    render: h => h(App),
}).$mount('#app');
