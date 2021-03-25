<template>
    <div>
        <el-button type="primary"
                   size="mini"
                   plain
                   @click="add">添加角色
        </el-button>
        <el-dialog :before-close="handleClose" :title="title" :visible.sync="dialogFormVisible">
            <el-form :rules="rules" ref="ruleForm" :model="roleDto">
                <el-input v-model="roleDto.id" style="display: none"/>
                <el-form-item label="角色" :label-width="formLabelWidth" prop="name">
                    <el-input v-model="roleDto.name" autocomplete="off"/>
                </el-form-item>
                <el-form-item label="描述" :label-width="formLabelWidth" prop="description">
                    <el-input type="textarea" v-model="roleDto.description" autocomplete="off"/>
                </el-form-item>
                <el-form-item label="资源权限" :label-width="formLabelWidth">
                    <el-tree
                            :data="resources"
                            show-checkbox
                            node-key="id"
                            ref="tree"
                            highlight-current
                            default-expand-all
                            :props="defaultProps">
                    </el-tree>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button type="primary" @click="saveOrUpdate('ruleForm')">提 交</el-button>
            </div>
        </el-dialog>
        <table id="simple-table" class="table table-bordered table-hover">
            <thead>
            <tr>
                <th>角色</th>
                <th>描述</th>
                <th>操作</th>
            </tr>
            </thead>

            <tbody>
            <tr v-for="role in roles">
                <th>{{ role.name}}</th>
                <th>{{ role.description}}</th>
                <td>
                    <div class="btn-group">
                        <el-tooltip class="item" effect="dark" content="更新" placement="top">
                            <el-button @click="get(role.id)"
                                       type="primary"
                                       icon="el-icon-edit"
                                       size="mini"/>
                        </el-tooltip>
                        <el-tooltip class="item" effect="dark" content="删除" placement="top">
                            <el-button @click="remove(role.id)"
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
        name: "role",
        data() {
            return {
                dialogFormVisible: false,
                formLabelWidth: '80px',
                title: "添加角色",
                roles: [], //数据显示列表
                resources: [],
                defaultProps: {
                    children: 'children',
                    label: 'name'
                },
                total: 0,
                currentPage: 1,
                size: 10,
                roleDto: {},
                rules: {
                    name: [
                        {required: true, message: '请输入角色', trigger: 'blur'},
                    ],
                    description: [
                        {required: true, message: '请输入描述', trigger: 'blur'},
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
                this.roleDto = {};
                this.$refs.tree.setCheckedKeys([]);
            },
            remove(id) {
                this.$confirm('此操作将删除该角色, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.$ajax
                        .get(process.env.VUE_APP_SERVER + "/api/auth/role/delete/" + id)
                        .then((response) => {
                            this.list();
                            let result = response.data;
                            if (result.code === 200) {
                                this.msg('success', result.msg);
                            } else {
                                this.msg('error', result.msg);
                            }
                        })
                        ;
                }).catch(() => {
                    this.msg('info', '已取消删除');
                });

            },
            //获取信息
            get(id) {
                this.title = "修改角色信息";
                this.dialogFormVisible = true;
                //获取要更新的对象
                this.$ajax
                    .get(process.env.VUE_APP_SERVER + "/api/auth/role/" + id)
                    .then((response) => {
                        let result = response.data;
                        this.roleDto = result.data;
                    })
                    ;
                this.$ajax
                    .get(process.env.VUE_APP_SERVER + "/api/auth/roleResource/" + id)
                    .then((response) => {
                        let result = response.data;
                        if (result.data != null) {
                            this.$refs.tree.setCheckedKeys(result.data);
                        } else {
                            this.$refs.tree.setCheckedKeys([]);
                        }
                    })
                    ;
            },
            //添加或更新
            saveOrUpdate(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        this.$ajax
                            .post(process.env.VUE_APP_SERVER + "/api/auth/role/saveOrUpdate", this.roleDto)
                            .then((response) => {
                                let result = response.data;
                                if (result.code === 200) {
                                    let roleId = result.data;
                                    this.$ajax.post(process.env.VUE_APP_SERVER + "/api/auth/roleResource/saveOrUpdate",
                                        {
                                            roleId: roleId,
                                            resourceId: this.$refs.tree.getCheckedKeys()
                                        }).then((response2) => {
                                        let result2 = response2.data;
                                        if (result2.code === 200) {
                                            this.msg('success', result2.msg);
                                        } else {
                                            this.msg('error', result2.msg);
                                        }
                                    });

                                } else {
                                    this.msg('error', result.msg);
                                }

                                this.dialogFormVisible = false;
                                this.list();
                            })
                            ;
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });

            },

            //分页
            list() {
                this.$ajax
                    .post(process.env.VUE_APP_SERVER + "/api/auth/role/list", {
                        page: this.currentPage,
                        size: this.size
                    })
                    .then((response) => {
                        if (response.data != null) {
                            let result = response.data;
                            this.roles = result.data.list;
                            this.total = result.count;
                        }
                    })
                    ;
                this.$ajax
                    .get(process.env.VUE_APP_SERVER + "/api/auth/resource/list")
                    .then((response) => {
                        if (response.data != null) {
                            let result = response.data;
                            this.resources = result.data;
                        }
                    })
                    ;
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
                    .catch(_ => {
                    });
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