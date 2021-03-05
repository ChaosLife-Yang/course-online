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
import locale from "element-ui/src/locale/lang/zh-CN";

window.$ = jQuery;
window.jQuery = jQuery;
Vue.use(UUID);

Vue.use(VueFroala);

Vue.use(ElementUI, {locale});
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

//刷新令牌请求
const refreshApply = next => {
    axios.post(process.env.VUE_APP_SERVER + "/api/auth/refreshToken",
        {
            clientId: clientId,
            clientSecret: clientSecret,
            token: LocalStorage.get(REFRESH_TOKEN)
        }).then(response => {
        let result = response.data;
        if (result.code === 200) {
            let _token = result.data;
            LocalStorage.set(ACCESS_TOKEN, _token.access_token);
            LocalStorage.set(REFRESH_TOKEN, _token.refresh_token);
            //保存用户信息
            let info = parseInfo(_token.access_token);
            let refresh = parseInfo(_token.refresh_token);
            LocalStorage.set(USER_INFO, info);
            LocalStorage.set(REFRESH_INFO, refresh);
            //使用刷新令牌请求成功 进行路由跳转
            if (next) {
                next();
            }
        } else {
            //刷新令牌都请求失败了那就重新登录
            LocalStorage.remove(ACCESS_TOKEN);
            LocalStorage.remove(REFRESH_TOKEN);
            LocalStorage.remove(USER_INFO);
            LocalStorage.remove(REFRESH_INFO);
            router.replace({
                path: '/login',
                query: {redirect: router.currentRoute.fullPath}
            });
        }
    });
};

//监控
const pushHandler = (next) => {
    let time = Date.parse(new Date()) / 1000;
    if (!Tool.isEmpty(LocalStorage.get(ACCESS_TOKEN)) && !Tool.isEmpty(LocalStorage.get(USER_INFO)) && LocalStorage.get(USER_INFO).exp > time) {
        if (next) {
            next();
        }
    } else if (!Tool.isEmpty(LocalStorage.get(REFRESH_TOKEN)) && !Tool.isEmpty(LocalStorage.get(REFRESH_INFO)) && LocalStorage.get(REFRESH_INFO).exp > time) {
        refreshApply(next);
    } else {
        console.log("重新登录");
        router.replace({
            path: '/login',
            query: {redirect: router.currentRoute.fullPath}
        });
    }
};

//路由前置监听
router.beforeEach((to, from, next) => {
    //路由需要鉴权 而缺失令牌或令牌过期就看刷新令牌申请令牌 刷新令牌也过期就重定向登录 令牌申请出错就重新登录
    if (to.meta.requiresAuth === true) {
        pushHandler(next);
    } else {
        next();
    }
});
//路由后置监听
router.afterEach((to, from) => {
    if (to.meta.requiresAuth === true) {
        pushHandler();
    }
});

/**
 * axios拦截器
 */
axios.interceptors.request.use(config => {
    //前端判断令牌是否过期 如果过期而刷新令牌没有过期就重新申请令牌 否则重新登录 令牌申请出错也重新登录
    let time = Date.parse(new Date()) / 1000;
    let exp;
    let refreshExp;
    if (!Tool.isEmpty(LocalStorage.get(USER_INFO)) && !Tool.isEmpty(LocalStorage.get(REFRESH_INFO))) {
        exp = LocalStorage.get(USER_INFO).exp;
        refreshExp = LocalStorage.get(REFRESH_INFO).exp;
    } else {
        exp = 0;
        refreshExp = 0;
    }
    if (exp > time) {
        config.headers.Authorization = `Bearer ${LocalStorage.get(ACCESS_TOKEN)}`;
    } else if (refreshExp > time) {
        console.log("刷新令牌请求");
    } else {
        LocalStorage.remove(ACCESS_TOKEN);
        LocalStorage.remove(REFRESH_TOKEN);
        LocalStorage.remove(USER_INFO);
        LocalStorage.remove(REFRESH_INFO);
        router.replace({
            path: '/login',
            query: {redirect: router.currentRoute.fullPath}
        });
    }
    console.log("请求：", config);
    return config;
}, error => {
    console.log(error);
    return Promise.reject(error);
});
//请求队列
const queue = [];
//避免重复请求刷新令牌
let refreshFlag = false;
//响应拦截器
axios.interceptors.response.use(response => {
    console.log("返回结果：", response);
    console.log("返回结果码：", response.data.code);
    let code = response.data.code;
    if (code === 401 || code === 402 || code === 609) {
        //重新申请令牌 申请成功就重新请求失败的接口
        //令牌失效将请求放入队列 请求一次刷新令牌之后再依次执行失败的方法 方法都是异步的所以不会阻塞
        console.log("请求入队列");
        queue.push(response.config);
        if (refreshFlag === false) {
            refreshFlag = true;
            refreshApply(() => {
                for (let i = 0; i < queue.length; i++) {
                    console.log("重新请求");
                    axios.request(queue.pop());
                }
                refreshFlag = false;
            });
        }
        return undefined;
    }
    return response;
}, error => {
    console.log(error);
    Message({
        showClose: true,
        type: 'error',
        message: error
    });
});

new Vue({
    router,
    render: h => h(App)
}).$mount('#app');
