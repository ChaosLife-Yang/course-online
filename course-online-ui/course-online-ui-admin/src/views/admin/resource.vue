<template>
    <div>
        <el-button type="primary"
                   size="mini"
                   plain
                   @click="add">添加资源
        </el-button>
        <el-dialog :before-close="handleClose" :title="title" :visible.sync="dialogFormVisible">
            <el-form :rules="rules" ref="ruleForm" :model="resourceDto">
                <el-input v-model="resourceDto.id" style="display: none"/>
                <el-form-item label="名称" :label-width="formLabelWidth" prop="name">
                    <el-input v-model="resourceDto.name" autocomplete="off"/>
                </el-form-item>
                <el-form-item label="权限标识" :label-width="formLabelWidth" prop="permissionValue">
                    <el-input v-model="resourceDto.permissionValue" autocomplete="off"/>
                </el-form-item>
                <el-form-item label="页面路由" :label-width="formLabelWidth" prop="page">
                    <el-input v-model="resourceDto.page" autocomplete="off"/>
                </el-form-item>
                <el-form-item label="后端接口" :label-width="formLabelWidth" prop="request">
                    <el-input v-model="resourceDto.request" autocomplete="off"/>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button type="primary" @click="saveOrUpdate('ruleForm')">提 交</el-button>
            </div>
        </el-dialog>
        <el-table
                :data="resources"
                style="width: 100%;"
                row-key="id"
                border
                default-expand-all
                highlight-current-row
                :tree-props="{children: 'children', hasChildren: 'hasChildren'}">

            <el-table-column
                    prop="name"
                    label="姓名"
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="permissionValue"
                    label="权限标识">
            </el-table-column>
            <el-table-column
                    prop="page"
                    :show-overflow-tooltip='true'
                    label="页面路由">
            </el-table-column>
            <el-table-column
                    prop="request"
                    :show-overflow-tooltip='true'
                    label="后端接口">
            </el-table-column>
            <el-table-column
                    label="操作">
                <template slot-scope="scope">
                    <el-tooltip class="item" effect="dark" content="添加子节点" placement="top">
                        <el-button @click="addChild(scope.row.id)"
                                   type="primary"
                                   icon="el-icon-plus"
                                   plain
                                   size="mini"/>
                    </el-tooltip>
                    <el-tooltip class="item" effect="dark" content="更新" placement="top">
                        <el-button @click="get(scope.row.id)"
                                   type="primary"
                                   icon="el-icon-edit"
                                   size="mini"/>
                    </el-tooltip>
                    <el-tooltip class="item" effect="dark" content="删除" placement="top">
                        <el-button @click="remove(scope.row.id)"
                                   type="danger"
                                   icon="el-icon-delete"
                                   size="mini"/>
                    </el-tooltip>
                </template>
            </el-table-column>
        </el-table>
    </div>
</template>

<script>
    export default {
        name: "resource",
        data() {
            return {
                dialogFormVisible: false,
                formLabelWidth: '80px',
                title: "添加资源",
                resources: [], //数据显示列表
                total: 0,
                currentPage: 1,
                size: 10,
                resourceDto: {},
                rules: {
                    name: [
                        {required: true, message: '请输入名称', trigger: 'blur'},
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
                this.resourceDto = {};
            },
            remove(id) {
                this.$confirm('此操作将删除该资源, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.$ajax
                        .get(process.env.VUE_APP_SERVER + "/api/auth/resource/delete/" + id)
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
            addChild(id) {
                this.title = "添加子资源";
                this.dialogFormVisible = true;
                this.resourceDto = {};
                this.resourceDto.parent = id;
            },
            //获取信息
            get(id) {
                this.title = "修改资源信息";
                this.dialogFormVisible = true;
                //获取要更新的对象
                this.$ajax
                    .get(process.env.VUE_APP_SERVER + "/api/auth/resource/" + id)
                    .then((response) => {
                        let result = response.data;
                        this.resourceDto = result.data;
                    })
                    ;
            },
            //添加或更新
            saveOrUpdate(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        this.$ajax
                            .post(process.env.VUE_APP_SERVER + "/api/auth/resource/saveOrUpdate", this.resourceDto)
                            .then((response) => {
                                let result = response.data;
                                if (result.code === 200) {
                                    this.msg('success', result.msg);
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

            list() {
                this.$ajax
                    .get(process.env.VUE_APP_SERVER + "/api/auth/resource/list")
                    .then((response) => {
                        if (response.data!=null){
                            let result = response.data;
                            this.resources = result.data;
                            this.total = result.count;

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