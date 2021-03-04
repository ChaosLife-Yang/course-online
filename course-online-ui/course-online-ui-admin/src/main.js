import Vue from 'vue'
import App from './App.vue'
import router from './router'
import axios from 'axios'
import filter from './filter/filter'

import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

import VueFroala from 'vue-froala-wysiwyg'

// Import and use Vue Froala lib.
import 'froala-editor/css/froala_editor.pkgd.min.css'
import 'froala-editor/css/froala_style.min.css'
import 'froala-editor/css/plugins/colors.min.css'
import 'froala-editor/css/plugins/colors.min.css'
import 'froala-editor/css/themes/gray.css'
import 'font-awesome/css/font-awesome.min.css'
//Import Froala Editor
import 'froala-editor/js/plugins.pkgd.min.js';
import 'froala-editor/js/languages/zh_cn.js'
import 'froala-editor/js/plugins/colors.min.js'
import 'froala-editor/js/plugins/emoticons.min.js'
import 'froala-editor/js/plugins/code_beautifier.min.js'
//Import third party plugins
import 'froala-editor/js/third_party/embedly.min';
import 'froala-editor/js/third_party/font_awesome.min';
import 'froala-editor/js/third_party/spell_checker.min';
import 'froala-editor/js/third_party/image_tui.min';
import 'froala-editor/js/froala_editor.pkgd.min.js'

import jQuery from 'jquery';
import UUID from 'vue-uuid'
import Message from "element-ui/packages/message";
import {parseInfo} from "./utils/tokenParser";

window.$ = jQuery;
window.jQuery = jQuery;
Vue.use(UUID);

Vue.use(VueFroala);

Vue.use(ElementUI);
Vue.prototype.$ajax = axios;
Vue.config.productionTip = false;
//去掉警告
Vue.config.silent = true;

// 全局过滤器
Object.keys(filter).forEach(key => {
    Vue.filter(key, filter[key])
});

const clientId = "auth_server";
const clientSecret = "123456";

Vue.prototype.$SECTION_CHARGE = [{key: "C", value: "收费"}, {key: "F", value: "免费"}];
Vue.prototype.$YES_NO = [{key: "1", value: "是"}, {key: "0", value: "否"}];
Vue.prototype.$COURSE_LEVEL = [{key: "1", value: "初级"}, {key: "2", value: "中级"}, {key: "3", value: "高级"}];
Vue.prototype.$COURSE_CHARGE = [{key: "C", value: "收费"}, {key: "F", value: "免费"}];
Vue.prototype.$COURSE_STATUS = [{key: "P", value: "发布"}, {key: "D", value: "草稿"}];
Vue.prototype.$FILE_USE = [{key: "C", value: "课程"}, {key: "T", value: "讲师"}];
Vue.prototype.$SMS_USE = [{key: "R", value: "注册"}, {key: "F", value: "忘记密码"}];
Vue.prototype.$SMS_STATUS = [{key: "U", value: "已使用"}, {key: "N", value: "未使用"}];
Vue.prototype.$SECTION_CHARGE_ARRAY = [{key: "C", value: "收费"}, {key: "F", value: "免费"}];
Vue.prototype.$YES_NO_ARRAY = [{key: "1", value: "是"}, {key: "0", value: "否"}];
Vue.prototype.$COURSE_LEVEL_ARRAY = [{key: "1", value: "初级"}, {key: "2", value: "中级"}, {key: "3", value: "高级"}];
Vue.prototype.$COURSE_CHARGE_ARRAY = [{key: "C", value: "收费"}, {key: "F", value: "免费"}];
Vue.prototype.$COURSE_STATUS_ARRAY = [{key: "P", value: "发布"}, {key: "D", value: "草稿"}];
Vue.prototype.$FILE_USE_ARRAY = [{key: "C", value: "课程"}, {key: "T", value: "讲师"}];
Vue.prototype.$SMS_USE_ARRAY = [{key: "R", value: "注册"}, {key: "F", value: "忘记密码"}];
Vue.prototype.$SMS_STATUS_ARRAY = [{key: "U", value: "已使用"}, {key: "N", value: "未使用"}];
Vue.prototype.$client_id = clientId;
Vue.prototype.$client_secret = clientSecret;

//路由监听
router.beforeEach((to, from, next) => {
    next();
});

/**
 * axios拦截器
 */
axios.interceptors.request.use(config => {
    console.log("请求：", config);
    return config;
}, error => {
    console.log(error);
});

axios.interceptors.response.use(response => {
    console.log("返回结果：", response);
    return response;
}, error => {
    console.log(error);
    Message({
        showClose: true,
        type: 'error',
        message: error
    });
    // router.replace({
    //     path: '/login',
    //     query: {redirect: router.currentRoute.fullPath}
    // });
});

new Vue({
    router,
    render: h => h(App)
}).$mount('#app');
