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

Vue.prototype.$SECTION_CHARGE=[{key:"C", value:"收费"},{key:"F", value:"免费"}];
Vue.prototype.$YES_NO=[{key:"1", value:"是"},{key:"0", value:"否"}];
Vue.prototype.$COURSE_LEVEL=[{key:"1", value:"初级"},{key:"2", value:"中级"},{key:"3", value:"高级"}];
Vue.prototype.$COURSE_CHARGE=[{key:"C", value:"收费"},{key:"F", value:"免费"}];
Vue.prototype.$COURSE_STATUS=[{key:"P", value:"发布"},{key:"D", value:"草稿"}];
Vue.prototype.$FILE_USE=[{key:"C", value:"课程"},{key:"T", value:"讲师"}];
Vue.prototype.$SMS_USE=[{key:"R", value:"注册"},{key:"F", value:"忘记密码"}];
Vue.prototype.$SMS_STATUS=[{key:"U", value:"已使用"},{key:"N", value:"未使用"}];

Vue.prototype.$SECTION_CHARGE_ARRAY=[{key:"C", value:"收费"},{key:"F", value:"免费"}];
Vue.prototype.$YES_NO_ARRAY=[{key:"1", value:"是"},{key:"0", value:"否"}];
Vue.prototype.$COURSE_LEVEL_ARRAY=[{key:"1", value:"初级"},{key:"2", value:"中级"},{key:"3", value:"高级"}];
Vue.prototype.$COURSE_CHARGE_ARRAY=[{key:"C", value:"收费"},{key:"F", value:"免费"}];
Vue.prototype.$COURSE_STATUS_ARRAY=[{key:"P", value:"发布"},{key:"D", value:"草稿"}];
Vue.prototype.$FILE_USE_ARRAY=[{key:"C", value:"课程"},{key:"T", value:"讲师"}];
Vue.prototype.$SMS_USE_ARRAY=[{key:"R", value:"注册"},{key:"F", value:"忘记密码"}];
Vue.prototype.$SMS_STATUS_ARRAY=[{key:"U", value:"已使用"},{key:"N", value:"未使用"}];


new Vue({
    router,
    render: h => h(App)
}).$mount('#app')
