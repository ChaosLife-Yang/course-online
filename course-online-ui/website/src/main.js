import Vue from 'vue'
import App from './App.vue'
import router from "./router";
import axios from "axios";

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

import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import locale from "element-ui/src/locale/lang/zh-CN";

import jQuery from 'jquery';

window.$ = jQuery;
window.jQuery = jQuery;

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
