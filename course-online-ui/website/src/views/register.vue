<template>
    <main role="main">
        <div class="album py-5 bg-light">
            <p style="text-align: center;font-weight: bold;font-size: 35px">
                Sign up
            </p>
            <el-card class="box-card" shadow="always">
                <el-form style="font-size: 8px" label-position="top" :rules="rules" label-width="200px"
                         :model="register">
                    <el-form-item label="手机号" prop="mobile">
                        <el-input placeholder="请输入手机号" v-model="register.mobile" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="昵称" prop="name">
                        <el-input placeholder="请输入昵称" type="password" v-model="register.name"
                                  autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="头像" prop="image">
                    </el-form-item>
                    <el-form-item label="密码" prop="password">
                        <el-input placeholder="请输入密码" type="password" v-model="register.password"
                                  autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="确认密码" prop="checkPass">
                        <el-input placeholder="请再次输入密码" type="password" v-model="register.checkPass"
                                  autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button style="width: 100%" type="success">注册并登录</el-button>
                    </el-form-item>
                </el-form>
            </el-card>

        </div>
    </main>
</template>

<script>
    export default {
        name: "register",
        data() {
            const validatePass = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请再次输入密码'));
                } else if (value !== this.register.password) {
                    callback(new Error('两次输入密码不一致!'));
                } else {
                    callback();
                }
            };
            return {
                register: {
                    mobile: '',
                    name: '',
                    photo: '',
                    password: '',
                    checkPass: '',
                },
                rules: {
                    mobile: [
                        {required: true, message: '请输入手机号', trigger: 'blur'}
                    ],
                    name: [
                        {required: true, message: '请输入昵称', trigger: 'blur'}
                    ],
                    password: [
                        {required: true, message: '请输入密码', trigger: 'blur'}
                    ],
                    checkPass: [
                        {required: true, validator: validatePass, trigger: 'blur'}
                    ],
                }
            }
        },
        methods: {
            toRegister() {

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