<template>
    <div>
        <el-button size="mini" plain>
            <router-link to="/business/course">
                <i class="ace-icon fa fa-arrow-left"></i>
                返回课程列表
            </router-link>
        </el-button>
        <h4 class="lighter">
            <i class="ace-icon fa fa-hand-o-right icon-animated-hand-pointer blue"></i>
            {{course.name}}
        </h4>

        <el-button type="primary"
                   size="mini"
                   plain
                   @click="add">添加大章
        </el-button>
        <el-dialog :title="title" :before-close="handleClose" :visible.sync="dialogFormVisible">
            <el-form :rules="rules" ref="ruleForm" :model="chapterDto">
                <el-input v-model="chapterDto.id" style="display: none"/>
                <el-form-item label="大章名称" :label-width="formLabelWidth" prop="name">
                    <el-input v-model="chapterDto.name" autocomplete="off"/>
                </el-form-item>
                <el-form-item label="大章顺序" :label-width="formLabelWidth" prop="sort">
                    <el-input v-model="chapterDto.sort" autocomplete="off"/>
                </el-form-item>
                <el-form-item label="所属课程" :label-width="formLabelWidth" prop="courseId">
                    <p class="form-control-static">{{course.name}}</p>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button :loading="loading" type="primary" @click="saveOrUpdate('ruleForm')">确 定</el-button>
            </div>
        </el-dialog>
        <table id="simple-table" class="table table-bordered table-hover">
            <thead>
            <tr>
                <th>名称</th>
                <th>课程</th>
                <th>顺序</th>
                <th>操作</th>
            </tr>
            </thead>

            <tbody>
            <tr v-for="chapter in chapters">
                <td>{{ chapter.name }}</td>
                <td>{{ course.name }}</td>
                <td>{{ chapter.sort }}</td>
                <td>
                    <div class="btn-group">
                        <el-button @click="toSection(chapter)"
                                   type="primary"
                                   plain
                                   size="mini">小节
                        </el-button>
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
                course: {},
                dialogFormVisible: false,
                loading: false,
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
                    sort: [
                        {required: true, message: '请输入大章顺序', trigger: 'blur'},
                    ],
                },
            }
        },

        created() {
            this.course = SessionStorage.get(SESSION_KEY_COURSE) || {};
            if (Tool.isEmpty(this.course)) {
                this.$router.push("/business/course");
            }
            this.chapterDto.courseId = this.course.id;
            this.list();
        },
        methods: {
            /**
             * 点击【小节】
             */
            toSection(chapter) {
                let _this = this;
                SessionStorage.set(SESSION_KEY_CHAPTER, chapter);
                _this.$router.push("/business/section");
            },
            msg(type, message) {
                this.$message({
                    showClose: true,
                    type: type,
                    message: message
                });
            },
            add() {
                this.dialogFormVisible = true;
                this.chapterDto.name = "";
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
                this.title = "修改大章信息";
                this.dialogFormVisible = true;
                //获取要更新的对象
                this.$ajax
                    .get(process.env.VUE_APP_SERVER + "/api/service/chapter/" + id)
                    .then((response) => {
                        let result = response.data;
                        this.chapterDto = result.data;
                    })
                    ;
            },
            //添加或更新
            saveOrUpdate(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        this.loading = true;
                        this.$ajax
                            .post(process.env.VUE_APP_SERVER + "/api/service/chapter/saveOrUpdate", this.chapterDto)
                            .then((response) => {
                                let result = response.data;
                                if (result.code === 200) {
                                    this.msg('success', result.msg);
                                } else {
                                    this.msg('error', result.msg);
                                }
                                this.dialogFormVisible = false;
                                this.loading = false;
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
                    .post(process.env.VUE_APP_SERVER + "/api/service/chapter/list", {
                        courseId: this.chapterDto.courseId,
                        page: this.currentPage,
                        size: this.size
                    })
                    .then((response) => {
                        if (response.data != null) {
                            let result = response.data;
                            this.chapters = result.data.list;
                            this.total = result.count;
                        }
                    })
                    ;

            },
            handleClose(done) {
                this.$confirm('确认关闭？')
                    .then(_ => {
                        done();
                    })
                    .catch(_ => {
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