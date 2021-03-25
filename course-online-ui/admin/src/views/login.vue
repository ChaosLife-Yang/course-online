<template>
    <div class="main-container">
        <div class="main-content">
            <div class="row">
                <div class="col-sm-10 col-sm-offset-1">
                    <div class="login-container">
                        <div class="center">
                            <h1>
                                <i class="ace-icon fa fa-leaf green"></i>
                                <span class="blue" id="id-text2">  管理控制台登录</span>
                            </h1>
                            <h4 class="blue" id="id-company-text">&copy; YANGYUDI</h4>
                        </div>

                        <div class="space-6"></div>

                        <div class="position-relative">
                            <div id="login-box" class="login-box visible widget-box no-border">
                                <div class="widget-body">
                                    <div class="widget-main">
                                        <h4 class="header blue lighter bigger">
                                            <i class="ace-icon fa fa-coffee green"></i>
                                            请输入账号密码
                                        </h4>

                                        <div class="space-6"></div>

                                        <form>
                                            <fieldset>
                                                <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text" v-model="loginDto.username"
                                                                   class="form-control"
                                                                   placeholder="Username"/>
															<i class="ace-icon fa fa-user"></i>
														</span>
                                                </label>

                                                <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" v-model="loginDto.password"
                                                                   class="form-control"
                                                                   placeholder="Password"/>
															<i class="ace-icon fa fa-lock"></i>
														</span>
                                                </label>

                                                <div class="space"></div>

                                                <div class="clearfix">

                                                    <button type="button"
                                                            class="width-35 pull-right btn btn-sm btn-primary"
                                                            @click="login()">
                                                        <i class="ace-icon fa fa-key"></i>
                                                        <span class="bigger-110">登录</span>
                                                    </button>
                                                </div>

                                                <div class="space-4"></div>
                                            </fieldset>
                                        </form>
                                        <div class="space-6"></div>
                                    </div><!-- /.widget-main -->
                                </div><!-- /.widget-body -->
                            </div><!-- /.login-box -->
                        </div><!-- /.position-relative -->

                    </div>
                </div><!-- /.col -->
            </div><!-- /.row -->
        </div><!-- /.main-content -->
    </div><!-- /.main-container -->
</template>

<script>
    import {parseInfo} from '../utils/token-parser'

    export default {
        name: 'login',
        mounted() {
            $("body").removeClass("login-layout blur-login");
            $('body').attr('class', 'login-layout blur-login');
            LocalStorage.remove(ACCESS_TOKEN);
            LocalStorage.remove(REFRESH_TOKEN);
            LocalStorage.remove(USER_INFO);
            LocalStorage.remove(REFRESH_INFO);
        },
        data() {
            return {
                gateway: process.env.VUE_APP_SERVER,
                loginDto: {
                    username: '',
                    password: '',
                    clientId: this.$client_id,
                    clientSecret: this.$client_secret,
                }
            }
        },
        methods: {
            login() {
                this.$ajax.post(process.env.VUE_APP_SERVER + "/api/auth/login", this.loginDto)
                    .then((response) => {
                        if (response.data) {
                            let result = response.data;
                            if (result.code === 200) {
                                let token = result.data;
                                //保存token和刷新token
                                LocalStorage.set(ACCESS_TOKEN, token.access_token);
                                LocalStorage.set(REFRESH_TOKEN, token.refresh_token);
                                //保存用户信息
                                let info = parseInfo(token.access_token);
                                let refresh = parseInfo(token.refresh_token);
                                LocalStorage.set(USER_INFO, info);
                                LocalStorage.set(REFRESH_INFO, refresh);
                                this.$router.push("/");
                            } else {
                                this.$message({
                                    showClose: true,
                                    type: 'error',
                                    message: result.msg
                                });
                            }
                        }
                    });
            }
        }
    }
</script>

<style scoped>

</style>
