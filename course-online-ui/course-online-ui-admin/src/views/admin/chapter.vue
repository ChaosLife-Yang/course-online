<template>
    <div>
        <el-button type="primary"
                   size="mini"
                   plain
                   @click="add">添加大章
        </el-button>
        <el-dialog :title="title" :visible.sync="dialogFormVisible">
            <el-form :rules="rules" ref="ruleForm" :model="chapterDto">
                <el-input v-model="chapterDto.id" style="display: none"/>
                <el-form-item label="大章名称" :label-width="formLabelWidth" prop="name">
                    <el-input v-model="chapterDto.name" autocomplete="off"/>
                </el-form-item>
                <el-form-item label="所属课程" :label-width="formLabelWidth" prop="courseId">
                    <el-input v-model="chapterDto.courseId" autocomplete="off"/>
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
            <tr v-for="chapter in chapters">
                <td>{{ chapter.id }}</td>
                <td>{{ chapter.name }}</td>
                <td>{{ chapter.courseId }}</td>
                <td>
                    <div class="btn-group">
                        <el-tooltip class="item" effect="dark" content="更新" placement="top">
                            <el-button @click="get(chapter.id)"
                                       type="primary"
                                       icon="el-icon-edit"
                                       size="mini"/>
                        </el-tooltip>
                        <el-tooltip class="item" effect="dark" content="删除" placement="top">
                            <el-button @click="remove(chapter.id)"
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
        name: "chapter",
        data() {
            return {
                dialogFormVisible: false,
                formLabelWidth: '80px',
                title: "添加大章",
                chapters: [], //数据显示列表
                total: 0,
                currentPage: 1,
                size: 10,
                chapterDto: {
                    id: "",
                    name: "",
                    courseId: ""
                },
                rules: {
                    name: [
                        {required: true, message: '请输入大章名称', trigger: 'blur'},
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
                this.chapterDto.id = "";
                this.chapterDto.name = "";
                this.chapterDto.courseId = "";
            },
            remove(id) {
                this.$confirm('此操作将永久删除该大章, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.$ajax
                        .get(process.env.VUE_APP_SERVER + "/api/service/chapter/delete/" + id)
                        .then((response) => {
                            this.list();
                            let result = response.data;
                            if (result.code === 200){
                                this.$message({
                                    showClose: true,
                                    type: 'success',
                                    message: result.data
                                });
                            }else {
                                this.$message({
                                    showClose: true,
                                    type: 'error',
                                    message: result.data
                                });
                            }
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
                this.title = "修改大章信息";
                this.dialogFormVisible = true;
                //获取要更新的对象
                this.$ajax
                    .get(process.env.VUE_APP_SERVER + "/api/service/chapter/" + id)
                    .then((response) => {
                        let result = response.data;
                        this.chapterDto = result.data;
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
                            .post(process.env.VUE_APP_SERVER + "/api/service/chapter/saveOrUpdate", this.chapterDto)
                            .then((response) => {
                                let result = response.data;
                                this.chapterDto.id = "";
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
                    .post(process.env.VUE_APP_SERVER + "/api/service/chapter/list", {
                        page: this.currentPage,
                        size: this.size
                    })
                    .then((response) => {
                        let result = response.data;
                        this.chapters = result.data.list;
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