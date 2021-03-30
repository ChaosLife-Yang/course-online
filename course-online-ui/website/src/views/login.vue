<template>
    <main role="main">
        <div class="album py-5 bg-light">
            <p style="text-align: center;font-weight: bold;font-size: 35px">
                Sign in
            </p>
            <el-card class="box-card" shadow="never">
                <el-form style="font-size: 8px" label-position="top" label-width="200px" :rules="rules" :model="login">
                    <el-form-item label="手机号" prop="mobile">
                        <el-input placeholder="请输入手机号" v-model="login.mobile" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="密码" prop="password">
                        <el-input placeholder="请输入密码" type="password" v-model="login.password"
                                  autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button style="width: 100%" type="success">登录</el-button>
                    </el-form-item>
                </el-form>
            </el-card>

        </div>
    </main>
</template>

<script>
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
        methods: {
            toLogin() {

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