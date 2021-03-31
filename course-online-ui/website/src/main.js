import Vue from 'vue'
import App from './App.vue'
import router from "./router";
import axios from "axios";

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

import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import locale from "element-ui/src/locale/lang/zh-CN";

import jQuery from 'jquery';
import filter from "../../admin/src/filter/filter";

window.$ = jQuery;
window.jQuery = jQuery;

Vue.use(VueFroala);
Vue.use(ElementUI, {locale});
Vue.config.productionTip = false;
const bus = new Vue();
//用来做消息总线
Vue.prototype.$EventBus = bus;
Vue.prototype.$store = axios;
Vue.prototype.$COURSE_LEVEL_ARRAY = [{key: "1", value: "初级"}, {key: "2", value: "中级"}, {key: "3", value: "高级"}];

const leave = () => {
    LocalStorage.remove(ACCESS_TOKEN);
    LocalStorage.remove(TOKEN_INFO);
    LocalStorage.remove(USER_INFO);
    router.replace({
        path: '/login'
    });
};
//判断令牌过期
const judgment = () => {
    let time = Date.parse(new Date()) / 1000;
    let exp;
    if (!Tool.isEmpty(LocalStorage.get(TOKEN_INFO))) {
        exp = LocalStorage.get(TOKEN_INFO).exp;
    } else {
        exp = 0;
    }
    if (exp <= time) {
        bus.$emit('getUser', {});
    }
};
//路由前置监听 判断
router.beforeEach((to, from, next) => {
    judgment();
    next();
});
//路由后置监听
router.afterEach((to, from) => {
    judgment();
});

axios.interceptors.request.use(config => {
    let time = Date.parse(new Date()) / 1000;
    let exp;
    if (!Tool.isEmpty(LocalStorage.get(TOKEN_INFO))&&!Tool.isEmpty(LocalStorage.get(ACCESS_TOKEN))) {
        exp = LocalStorage.get(TOKEN_INFO).exp;
    } else {
        exp = 0;
    }
    if (exp > time) {
        config.headers.Authorization = `Bearer ${LocalStorage.get(ACCESS_TOKEN)}`;
    }
    console.log("请求：", config);
    return config;
}, error => {
    console.log(error);
    return Promise.reject(error);
});

//响应拦截器
axios.interceptors.response.use(response => {
    let code = response.data.code;
    if (code === 401) {
        leave();
        return undefined;
    }
    console.log("响应：", response);
    return response;
}, error => {
    console.log(error);
});

// 全局过滤器
Object.keys(filter).forEach(key => {
    Vue.filter(key, filter[key])
});
new Vue({
    router,
    render: h => h(App),
}).$mount('#app');
