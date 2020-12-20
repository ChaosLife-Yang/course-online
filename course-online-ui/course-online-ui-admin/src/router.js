import Vue from "vue"
import Router from "vue-router"
import Login from "./views/login.vue"
import Admin from "./views/admin.vue"
import Welcome from "./views/admin/welcome"
import Chapter from "./views/admin/chapter"
import Course from "./views/admin/course"
import Section from "./views/admin/section"


Vue.use(Router);

export default new Router({
    mode: "history",
    routes: [
        {
            path: "*",
            redirect: "/login",
        }, {
            path: "/login",
            component: Login,
        }, {
            path: "/",
            name: "admin",
            component: Admin,
            redirect: "/welcome",
            children: [
                {
                    //子路由不用加斜杠
                    path: 'welcome',
                    name: 'welcome',
                    component: Welcome,
                }, {
                    //子路由不用加斜杠
                    path: 'business/chapter',
                    name: 'business/chapter',
                    component: Chapter,
                }, {
                    //子路由不用加斜杠
                    path: 'business/course',
                    name: 'business/course',
                    component: Course,
                },{
                    //子路由不用加斜杠
                    path: 'business/section',
                    name: 'business/section',
                    component: Section,
                },
            ]
        },
    ]
})