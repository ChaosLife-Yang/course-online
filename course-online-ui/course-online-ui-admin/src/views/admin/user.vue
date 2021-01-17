<template>
    <div>
        <el-button type="primary"
                   size="mini"
                   plain
                   @click="add">添加用户
        </el-button>
        <el-dialog :before-close="handleClose" :title="title" :visible.sync="dialogFormVisible">
            <el-form :rules="rules" ref="ruleForm" :model="userDto">
                <el-input v-model="userDto.id" style="display: none"/>
                <el-form-item label="登陆名" :label-width="formLabelWidth" prop="loginName">
                    <el-input v-model="userDto.loginName" autocomplete="off"/>
                </el-form-item>
                <el-form-item label="昵称" :label-width="formLabelWidth" prop="name">
                    <el-input v-model="userDto.name" autocomplete="off"/>
                </el-form-item>
                <el-form-item label="密码" :label-width="formLabelWidth" prop="password">
                    <el-input placeholder="请输入密码" v-model="userDto.password" show-password></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button type="primary" @click="saveOrUpdate('ruleForm')">提 交</el-button>
            </div>
        </el-dialog>
        <table id="simple-table" class="table table-bordered table-hover">
            <thead>
            <tr>
                <th>登陆名</th>
                <th>昵称</th>
                <th>创建时间</th>
                <th>操作</th>
            </tr>
            </thead>

            <tbody>
            <tr v-for="user in users">
                <th>{{ user.loginName}}</th>
                <th>{{ user.name}}</th>
                <th>{{ user.createTime}}</th>
                <td>
                    <div class="btn-group">
                        <el-tooltip class="item" effect="dark" content="更新" placement="top">
                            <el-button @click="get(user.id)"
                                       type="primary"
                                       icon="el-icon-edit"
                                       size="mini"/>
                        </el-tooltip>
                        <el-tooltip class="item" effect="dark" content="删除" placement="top">
                            <el-button @click="remove(user.id)"
                                       type="danger"
                                       icon="el-icon-delete"
                                       size="mini"/>
                        </el-tooltip>
                    </div>

                </td>
            </tr>
            </tbody>
        </table>
        <div class="block">
            <el-pagination
                    background
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :current-page="currentPage"
                    :page-sizes="[5,10, 20, 50, 100]"
                    :page-size="size"
                    :page-count="7"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="total"/>
        </div>
    </div>
</template>

<script>
    export default {
        name: "user",
        data() {
            return {
                dialogFormVisible: false,
                formLabelWidth: '80px',
                title: "添加用户",
                users: [], //数据显示列表
                total: 0,
                currentPage: 1,
                size: 10,
                userDto: {},
                rules: {
                    loginName: [
                        {required: true, message: '请输入登陆名', trigger: 'blur'},
                    ],
                    name: [
                        {required: true, message: '请输入昵称', trigger: 'blur'},
                    ],
                    password: [
                        {required: true, message: '请输入密码', trigger: 'blur'},
                    ],
                },
            }
        },

        created() {
            this.list();
        },
        methods: {
            msg(type, message) {
                this.$message({
                    showClose: true,
                    type: type,
                    message: message
                });
            },
            add() {
                this.dialogFormVisible = true;
                this.userDto = {};
            },
            remove(id) {
                this.$confirm('此操作将删除该用户, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.$ajax
                        .get(process.env.VUE_APP_SERVER + "/api/auth/user/delete/" + id)
                        .then((response) => {
                            this.list();
                            let result = response.data;
                            if (result.code === 200) {
                                this.msg('success', result.msg);
                            }else {
                                this.msg('error', result.msg);
                            }
                        })
                        .catch(error => {
                            this.msg('error', error);
                        });
                }).catch(() => {
                    this.msg('info', '已取消删除');
                });

            },
            //获取信息
            get(id) {
                this.title = "修改用户信息";
                this.dialogFormVisible = true;
                //获取要更新的对象
                this.$ajax
                    .get(process.env.VUE_APP_SERVER + "/api/auth/user/" + id)
                    .then((response) => {
                        let result = response.data;
                        this.userDto = result.data;
                    })
                    .catch(error => {
                        this.msg('error', error);
                    });
            },
            //添加或更新
            saveOrUpdate(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        this.$ajax
                            .post(process.env.VUE_APP_SERVER + "/api/auth/user/saveOrUpdate", this.userDto)
                            .then((response) => {
                                let result = response.data;
                                this.userDto.id = "";
                                if (result.code === 200) {
                                    this.msg('success', result.msg);
                                    this.dialogFormVisible = false;
                                    this.list();
                                }else {
                                    this.msg('error', result.msg);
                                }
                            })
                            .catch(error => {
                                this.msg('error', error);
                            });
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });

            },

            //分页
            list() {
                this.$ajax
                    .post(process.env.VUE_APP_SERVER + "/api/auth/user/list", {
                        page: this.currentPage,
                        size: this.size
                    })
                    .then((response) => {
                        let result = response.data;
                        this.users = result.data.list;
                        this.total = result.count;
                    })
                    .catch(error => {
                        this.msg('error', error);
                    });

            },
            handleSizeChange(val) {
                this.size = val;
                this.list();
            },
            handleCurrentChange(val) {
                this.currentPage = val;
                this.list();
            },
            handleClose(done) {
                this.$confirm('确认关闭？')
                    .then(_ => {
                        done();
                    })
                    .catch(_ => {});
            }

        },
    };
</script>

<style scoped>
    .el-input {
        width: 80% !important;
        margin: 0 5px !important;
    }

</style>