<template>
    <main role="main">
        <div class="album py-5 bg-light">
            <p style="text-align: center;font-weight: bold;font-size: 35px">
                Sign up
            </p>
            <el-card class="box-card" shadow="always">
                <el-form style="font-size: 8px" label-position="top" ref="ruleForm" :rules="rules" label-width="200px"
                         :model="register">
                    <el-form-item label="手机号" prop="mobile">
                        <el-input placeholder="请输入手机号" v-model="register.mobile" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="昵称" prop="name">
                        <el-input placeholder="请输入昵称" v-model="register.name"
                                  autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="头像" prop="image">
                        <el-avatar style="display: block;float: left;" size="large" :src="register.photo"></el-avatar>
                        <shard-upload
                                style="display: block;float: left; margin-left: 10px"
                                :url="gateway+'/api/file/oss/upload'"
                                :check-url="gateway+'/api/file/local/check'"
                                :get-md5="gateway+'/api/file/local/getMd5'"
                                :button-name="'上传头像'"
                                :pause="false"
                                :use="'T'"
                                @getUrl="handleAvatarSuccess"
                                :before-upload="beforeAvatarUpload"/>
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
                        <el-button style="width: 100%" @click="submitForm('ruleForm')" :loading="loading"
                                   type="success">注册并登录
                        </el-button>
                    </el-form-item>
                </el-form>
            </el-card>

        </div>
    </main>
</template>

<script>
    import shardUpload from "../components/shardUpload";
    import {parseInfo} from "../utils/token-parser";

    export default {
        name: "register",
        components: {shardUpload},
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
                gateway: process.env.VUE_APP_SERVER,
                loading: false,
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
        created() {
            LocalStorage.remove(ACCESS_TOKEN);
            LocalStorage.remove(TOKEN_INFO);
            LocalStorage.remove(USER_INFO);
        },
        methods: {
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        this.toRegister();
                    } else {
                        return false;
                    }
                });
            },
            toRegister() {
                this.loading = true;
                this.$store.post(`${process.env.VUE_APP_SERVER}/api/front/register`, this.register)
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
                                    } else {
                                        this.$notify.error({
                                            title: '注册失败',
                                            message: response.data.msg || "账号或密码不正确"
                                        });
                                        this.loading = false;
                                    }
                                })
                                .catch(error => {
                                    console.log(error);
                                    this.loading = false;
                                });
                        } else {
                            this.$notify.error({
                                title: '注册失败',
                                message: response.data.msg || "账号或密码不正确"
                            });
                            this.loading = false;
                        }
                    })
                    .catch(error => {
                        console.log(error);
                        this.loading = false;
                    });
            },
            beforeAvatarUpload(file) {
                const isJPG = file.type === 'image/jpeg' || file.type === 'image/png';
                const isLt2M = file.size / (1024 * 1024) < 2;

                if (!isJPG) {
                    this.$message.error('上传头像图片只能是 JPG 或 PNG 格式!');
                }
                if (!isLt2M) {
                    this.$message.error('上传头像图片大小不能超过 2MB!');
                }
                return isJPG && isLt2M;
            },
            handleAvatarSuccess(url) {
                this.register.photo = url;
            },
        }
    }
</script>

<style scoped>

    .box-card {
        width: 350px;
        margin: 0 auto;
    }
</style>