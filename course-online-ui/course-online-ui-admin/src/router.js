import Vue from "vue"
import Router from "vue-router"
import Login from "./views/login.vue"
import Admin from "./views/admin.vue"
import Welcome from "./views/admin/welcome"
import Chapter from "./views/admin/chapter"
import Course from "./views/admin/course"
import Section from "./views/admin/section"
import Category from "./views/admin/category"
import Teacher from "./views/admin/teacher"
import File from "./views/admin/file"
import User from "./views/admin/user"
import Resource from "./views/admin/resource"
import Role from "./views/admin/role"
import Test from "./views/test"

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
            path: "/test",
            component: Test,
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
                }, {
                    //子路由不用加斜杠
                    path: 'business/section',
                    name: 'business/section',
                    component: Section,
                }, {
                    //子路由不用加斜杠
                    path: 'business/category',
                    name: 'business/category',
                    component: Category,
                }, {
                    //子路由不用加斜杠
                    path: 'business/teacher',
                    name: 'business/teacher',
                    component: Teacher,
                }, {
                    //子路由不用加斜杠
                    path: 'system/file',
                    name: 'system/file',
                    component: File,
                }, {
                    //子路由不用加斜杠
                    path: 'business/user',
                    name: 'business/user',
                    component: User,
                }, {
                    //子路由不用加斜杠
                    path: 'business/resource',
                    name: 'business/resource',
                    component: Resource,
                }, {
                    //子路由不用加斜杠
                    path: 'business/role',
                    name: 'business/role',
                    component: Role,
                },
            ]
        },
    ]
})