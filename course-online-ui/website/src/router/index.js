import Vue from 'vue'
import Router from 'vue-router'
import Index from '../views/index'
import List from '../views/list'
import Detail from '../views/detail'
import Login from '../views/login'
import Register from '../views/register'

Vue.use(Router);

export default new Router({
    mode: "history",
    routes: [
        {
            path: "/",
            component: Index
        },
        {
            path: "/list",
            component: List,
        },
        {
            path: "/detail/:id",
            component: Detail,
        },
        {
            path: "/login",
            component: Login,
        },
        {
            path: "/register",
            component: Register,
        },
        {
            path: "*",
            redirect: "/",
        }
    ]
})
