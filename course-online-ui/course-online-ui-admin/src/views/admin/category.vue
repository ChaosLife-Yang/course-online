<template>
    <div>
        <el-dialog :title="title" :visible.sync="dialogFormVisible">
            <el-form :rules="rules" ref="ruleForm" :model="categoryDto">
                <el-input v-model="categoryDto.id" style="display: none"/>
                <el-form-item label="名称" :label-width="formLabelWidth" prop="name">
                    <el-input v-model="categoryDto.name" autocomplete="off"/>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button type="primary" @click="saveOrUpdate('ruleForm')">确 定</el-button>
            </div>
        </el-dialog>
        <div class="row">
            <div class="col-md-6">
                <el-button type="primary"
                           size="mini"
                           plain
                           @click="add">添加一级分类
                </el-button>
                <el-dialog :title="title" :visible.sync="dialogFormVisible">
                    <el-form :rules="rules" ref="ruleForm" :model="categoryDto">
                        <el-input v-model="categoryDto.id" style="display: none"/>
                        <el-form-item label="名称" :label-width="formLabelWidth" prop="name">
                            <el-input v-model="categoryDto.name" autocomplete="off"/>
                        </el-form-item>
                    </el-form>
                    <div slot="footer" class="dialog-footer">
                        <el-button type="primary" @click="saveOrUpdate('ruleForm')">确 定</el-button>
                    </div>
                </el-dialog>
                <table id="simple-table" class="table table-bordered table-hover">
                    <thead>
                    <tr>
                        <th>名称</th>
                        <th>创建时间</th>
                        <th>操作</th>
                    </tr>
                    </thead>

                    <tbody>
                    <tr v-for="category in level1" v-on:click="onLevel1(category)">
                        <th>{{ category.name}}</th>
                        <th>{{ category.createTime}}</th>
                        <td>
                            <div class="btn-group">
                                <el-tooltip class="item" effect="dark" content="更新" placement="top">
                                    <el-button @click="get(category.id)"
                                               type="primary"
                                               icon="el-icon-edit"
                                               size="mini"/>
                                </el-tooltip>
                                <el-tooltip class="item" effect="dark" content="删除" placement="top">
                                    <el-button @click="remove(category.id)"
                                               type="danger"
                                               icon="el-icon-delete"
                                               size="mini"/>
                                </el-tooltip>
                            </div>

                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="col-md-6" v-if="showLv2">
                <el-button type="primary"
                           size="mini"
                           plain
                           @click="addLv2">添加二级分类
                </el-button>
                <h4 class="lighter">
                    <i class="ace-icon fa fa-hand-o-right icon-animated-hand-pointer blue"></i>
                    {{lv2Name}}
                </h4>
                <table id="simple-level2-table" class="table table-bordered table-hover" v-if="level2!=null">
                    <thead>
                    <tr>
                        <th>名称</th>
                        <th>创建时间</th>
                        <th>操作</th>
                    </tr>
                    </thead>

                    <tbody>
                    <tr v-for="category in level2">
                        <th>{{ category.name}}</th>
                        <th>{{ category.createTime}}</th>
                        <td>
                            <div class="btn-group">
                                <el-tooltip class="item" effect="dark" content="更新" placement="top">
                                    <el-button @click="get(category.id)"
                                               type="primary"
                                               icon="el-icon-edit"
                                               size="mini"/>
                                </el-tooltip>
                                <el-tooltip class="item" effect="dark" content="删除" placement="top">
                                    <el-button @click="remove(category.id)"
                                               type="danger"
                                               icon="el-icon-delete"
                                               size="mini"/>
                                </el-tooltip>
                            </div>

                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>

    </div>
</template>

<script>
    export default {
        name: "category",
        data() {
            return {
                dialogFormVisible: false,
                loading: false,
                formLabelWidth: '80px',
                title: "添加分类",
                categoryDto: {},
                level1: [],
                level2: [],
                lv2Name: "",
                showLv2: false,
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
                this.categoryDto = {};
            },
            addLv2() {
                this.dialogFormVisible = true;
                this.categoryDto = {};
                this.categoryDto.parent = SessionStorage.get("lv2");
            },
            remove(id) {
                this.$confirm('此操作将删除该分类, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.$ajax
                        .get(process.env.VUE_APP_SERVER + "/api/service/category/delete/" + id)
                        .then((response) => {
                            if (response.data != null) {
                                let result = response.data;
                                if (result.code === 200) {
                                    this.msg('success', result.msg);
                                    this.list();
                                } else {
                                    this.msg('error', result.msg);
                                }

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
                this.title = "修改分类信息";
                this.dialogFormVisible = true;
                //获取要更新的对象
                this.$ajax
                    .get(process.env.VUE_APP_SERVER + "/api/service/category/" + id)
                    .then((response) => {
                        if (response.data != null) {
                            let result = response.data;
                            this.categoryDto = result.data;
                        }
                    })
                    .catch(error => {
                        this.msg('error', error);
                    });
            },
            //添加或更新
            saveOrUpdate(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        this.loading = true;
                        this.$ajax
                            .post(process.env.VUE_APP_SERVER + "/api/service/category/saveOrUpdate", this.categoryDto)
                            .then((response) => {
                                if (response.data != null) {
                                    let result = response.data;
                                    if (result.code === 200) {
                                        this.msg('success', result.msg);
                                    } else {
                                        this.msg('error', result.msg);
                                    }
                                    this.dialogFormVisible = false;
                                    this.loading = false;
                                    this.list();
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

            //查询
            list() {
                this.$ajax
                    .get(process.env.VUE_APP_SERVER + "/api/service/category/list")
                    .then((response) => {
                        if (response.data != null) {
                            let result = response.data;
                            this.level1 = result.data;
                            if (this.showLv2) {
                                for (let i = 0; i < this.level1.length; i++) {
                                    if (this.level1[i].id === SessionStorage.get("lv2")) {
                                        this.level2 = this.level1[i].children;
                                    }
                                }
                            }
                        }

                    })
                    .catch(error => {
                        this.msg('error', error);
                    });

            },
            onLevel1(category) {
                if (Tool.isEmpty(category.children)) {
                    this.level2 = null;
                } else {
                    this.level2 = category.children;
                }
                this.lv2Name = category.name;
                this.showLv2 = true;
                SessionStorage.set("lv2", category.id);
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