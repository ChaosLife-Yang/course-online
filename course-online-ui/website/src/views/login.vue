<template>
    <main role="main">
        <div class="album py-5 bg-light">
            <p style="text-align: center;font-weight: bold;font-size: 35px">
                Sign in
            </p>
            <el-card class="box-card" shadow="always">
                <el-form style="font-size: 8px" label-position="top" label-width="200px" :ref="login" :rules="rules"
                         :model="login">
                    <el-form-item label="手机号" prop="mobile">
                        <el-input placeholder="请输入手机号" v-model="login.mobile" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="密码" prop="password">
                        <el-input placeholder="请输入密码" type="password" v-model="login.password"
                                  autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button style="width: 100%" @click="toLogin" :loading="loading" type="success">登录</el-button>
                    </el-form-item>
                </el-form>
            </el-card>

        </div>
    </main>
</template>

<script>
    import {parseInfo} from '../utils/token-parser'

    export default {
        name: "login",
        data() {
            const validateMobile = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请输入手机号'));
                } else {
                    callback();
                }
            };
            const validatePass = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请输入密码'));
                } else {
                    callback();
                }
            };
            return {
                loading: false,
                login: {
                    mobile: '',
                    password: '',
                },
                rules: {
                    mobile: [
                        {validator: validateMobile, trigger: 'blur'}
                    ],
                    password: [
                        {validator: validatePass, trigger: 'blur'}
                    ],
                }
            }
        },
        created() {
            LocalStorage.remove(ACCESS_TOKEN);
            LocalStorage.remove(TOKEN_INFO);
            LocalStorage.remove(USER_INFO);
        },
        methods: {
            toLogin() {
                this.loading = true;
                this.$store.post(`${process.env.VUE_APP_SERVER}/api/front/login`, this.login)
                    .then(response => {
                        if (response.data && response.data.code === 200) {
                            let result = response.data;
                            LocalStorage.set(ACCESS_TOKEN, result.data);
                            let data = parseInfo(result.data);
                            LocalStorage.set(TOKEN_INFO, data);
                            this.$store.get(`${process.env.VUE_APP_SERVER}/api/front/info/${data.id}`)
                                .then(resp => {
                                    if (resp.data && resp.data.code === 200) {
                                        LocalStorage.set(USER_INFO, resp.data.data);
                                        this.$EventBus.$emit('getUser', resp.data.data);
                                        this.loading = false;
                                        this.$router.push("/");
                                    }
                                })
                                .catch(error => {
                                    console.log(error);
                                    this.loading = false;
                                });
                        }
                    })
                    .catch(error => {
                        console.log(error);
                        this.loading = false;
                    });
            }
        }
    }
</script>

<style scoped>
    .box-card {
        width: 350px;
        margin: 0 auto;
    }
</style>