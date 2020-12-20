import Vue from 'vue'
import App from './App.vue'
import router from './router'
import axios from 'axios'
import filter from './filter/filter'

import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';


Vue.use(ElementUI);
Vue.prototype.$ajax = axios;
Vue.prototype.$charge = [{key: "C", value: "收费"}, {key: "F", value: "免费"}];

Vue.config.productionTip = false;

/**
 * axios拦截器
 */
axios.interceptors.request.use(function (config) {
    console.log("请求：", config);
    return config;
}, error => {
});

axios.interceptors.response.use(function (response) {
    console.log("返回结果：", response);
    return response;
}, error => {
});

// 全局过滤器
Object.keys(filter).forEach(key => {
    Vue.filter(key, filter[key])
});

new Vue({
    router,
    render: h => h(App)
}).$mount('#app')
