<template>
    <header>
        <el-backtop></el-backtop>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container">
                <router-link class="navbar-brand" to="/">
                    <i class="fa fa-video-camera"></i>&nbsp;YUDI
                </router-link>
                <button class="navbar-toggler" type="button" data-toggle="collapse"
                        data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                        aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item active">
                            <router-link class="nav-link" to="/">主页 <span class="sr-only">(current)</span></router-link>
                        </li>
                        <li class="nav-item active">
                            <router-link class="nav-link" to="/list">全部课程</router-link>
                        </li>
                    </ul>
                    <form class="form-inline my-2 my-lg-0" :action="baseUrl+'index'">
                        <input class="form-control mr-sm-2" name="words" type="search" placeholder="Search"
                               aria-label="Search">
                    </form>
                    <span style="font-size: 12px;font-family: 'Microsoft YaHei UI Light'" v-if="user.id" class="text-white pr-3">您好, {{user.name}}</span>
                    <button v-if="user.id" @click="logout()" class="btn btn-outline-light"><i class="el-icon-switch-button"></i>退出</button>
                    <button v-if="!user.id" @click="$router.push('/login')" class="btn btn-dark">登录</button>
                    <button v-if="!user.id" @click="$router.push('/register')" class="btn btn-outline-light">注册</button>
                </div>
            </div>

        </nav>
    </header>
</template>

<script>
    export default {
        name: "boot-header",
        data() {
            return {
                baseUrl: process.env.BASE_URL,
                user: {},
            }
        },
        mounted() {
            this.user = LocalStorage.get(USER_INFO) || {};
            this.$EventBus.$on('getUser', (data) => {
                this.user = data || {};
            })
        },
        methods: {
            logout() {
                this.$EventBus.$emit('getUser', {});
                LocalStorage.remove(ACCESS_TOKEN);
                LocalStorage.remove(TOKEN_INFO);
                LocalStorage.remove(USER_INFO);
            }
        }
    }
</script>

<style scoped>

</style>