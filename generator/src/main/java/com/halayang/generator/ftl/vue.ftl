<template>
    <div>
        <el-button type="primary"
                   size="mini"
                   plain
                   @click="add">添加${moduleName}
        </el-button>
        <el-dialog :title="title" :visible.sync="dialogFormVisible">
            <el-form :rules="rules" ref="ruleForm" :model="${domain}Dto">
                <el-input v-model="${domain}Dto.id" style="display: none"/>
                <el-form-item label="${moduleName}名称" :label-width="formLabelWidth" prop="name">
                    <el-input v-model="${domain}Dto.name" autocomplete="off"/>
                </el-form-item>
                <el-form-item label="所属课程" :label-width="formLabelWidth" prop="courseId">
                    <el-input v-model="${domain}Dto.courseId" autocomplete="off"/>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="saveOrUpdate('ruleForm')">确 定</el-button>
            </div>
        </el-dialog>
        <table id="simple-table" class="table table-bordered table-hover">
            <thead>
            <tr>
                <th>id</th>
                <th>名称</th>
                <th>课程id</th>
                <th>操作</th>
            </tr>
            </thead>

            <tbody>
            <tr v-for="${domain} in ${domain}s">
                <td>{{ ${domain}.id }}</td>
                <td>{{ ${domain}.name }}</td>
                <td>{{ ${domain}.courseId }}</td>
                <td>
                    <div class="btn-group">
                        <el-tooltip class="item" effect="dark" content="更新" placement="top">
                            <el-button @click="get(${domain}.id)"
                                       type="primary"
                                       icon="el-icon-edit"
                                       size="mini"/>
                        </el-tooltip>
                        <el-tooltip class="item" effect="dark" content="删除" placement="top">
                            <el-button @click="remove(${domain}.id)"
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
        name: "${domain}",
        data() {
            return {
                dialogFormVisible: false,
                formLabelWidth: '80px',
                title: "添加${moduleName}",
                ${domain}s: [], //数据显示列表
                total: 0,
                currentPage: 1,
                size: 10,
                ${domain}Dto: {
                    id: "",
                    name: "",
                    courseId: ""
                },
                rules: {
                    name: [
                        {required: true, message: '请输入${moduleName}名称', trigger: 'blur'},
                    ],
                    courseId: [
                        {required: true, message: '请输入课程名称', trigger: 'blur'},
                    ],
                },
            }
        },

        created() {
            this.list();
        },
        methods: {
            add() {
                this.dialogFormVisible = true;
                this.${domain}Dto.id = "";
                this.${domain}Dto.name = "";
                this.${domain}Dto.courseId = "";
            },
            remove(id) {
                this.$confirm('此操作将永久删除该${moduleName}, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.$ajax
                        .get(process.env.VUE_APP_SERVER + "/api/service/${domain}/delete/" + id)
                        .then((response) => {
                            this.list();
                            let result = response.data;
                            this.$message({
                                showClose: true,
                                type: 'success',
                                message: result.data
                            });
                        })
                        .catch(error => {
                            this.$message({
                                showClose: true,
                                message: error,
                                type: 'error'
                            });
                        });
                }).catch(() => {
                    this.$message({
                        showClose: true,
                        type: 'info',
                        message: '已取消删除'
                    });
                });

            },
            get(id) {
                this.title = "修改${moduleName}信息";
                this.dialogFormVisible = true;
                //获取要更新的对象
                this.$ajax
                    .get(process.env.VUE_APP_SERVER + "/api/service/${domain}/" + id)
                    .then((response) => {
                        let result = response.data;
                        this.${domain}Dto = result.data;
                    })
                    .catch(error => {
                        this.$message({
                            showClose: true,
                            message: error,
                            type: 'error'
                        });
                    });
            },
            saveOrUpdate(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        this.$ajax
                            .post(process.env.VUE_APP_SERVER + "/api/service/${domain}/saveOrUpdate", this.${domain}Dto)
                            .then((response) => {
                                let result = response.data;
                                this.${domain}Dto.id = "";
                                this.$message({
                                    showClose: true,
                                    message: result.msg,
                                    type: 'success'
                                });
                                this.dialogFormVisible = false;
                                this.list();
                            })
                            .catch(error => {
                                this.$message({
                                    showClose: true,
                                    message: error,
                                    type: 'error'
                                });
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
                    .post(process.env.VUE_APP_SERVER + "/api/service/${domain}/list", {
                        page: this.currentPage,
                        size: this.size
                    })
                    .then((response) => {
                        let result = response.data;
                        this.${domain}s = result.data.list;
                        this.total = result.count;
                    })
                    .catch(error => {
                        this.$message({
                            showClose: true,
                            message: error,
                            type: 'error'
                        });
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

        },
    };
</script>

<style scoped>
    .el-input {
        width: 80% !important;
        margin: 0 5px !important;
    }

</style>