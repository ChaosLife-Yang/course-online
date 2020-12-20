<template>
    <div>
        <el-button type="primary"
                   size="mini"
                   plain
                   @click="add">添加课程
        </el-button>
        <el-dialog :title="title" :visible.sync="dialogFormVisible">
            <el-form :rules="rules" ref="ruleForm" :model="courseDto">
                <el-input v-model="courseDto.id" style="display: none"/>
                <el-form-item label="名称" :label-width="formLabelWidth" prop="name">
                    <el-input v-model="courseDto.name" autocomplete="off"/>
                </el-form-item>
                <el-form-item label="概述" :label-width="formLabelWidth" prop="summary">
                    <el-input v-model="courseDto.summary" autocomplete="off"/>
                </el-form-item>
                <el-form-item label="时长" :label-width="formLabelWidth" prop="time">
                    <el-input v-model="courseDto.time" autocomplete="off"/>
                </el-form-item>
                <el-form-item label="价格（元）" :label-width="formLabelWidth" prop="price">
                    <el-input v-model="courseDto.price" autocomplete="off"/>
                </el-form-item>
                <el-form-item label="封面" :label-width="formLabelWidth" prop="image">
                    <el-input v-model="courseDto.image" autocomplete="off"/>
                </el-form-item>
                <el-form-item label="级别" :label-width="formLabelWidth" prop="level">
                    <el-input v-model="courseDto.level" autocomplete="off"/>
                </el-form-item>
                <el-form-item label="收费" :label-width="formLabelWidth" prop="charge">
                    <el-input v-model="courseDto.charge" autocomplete="off"/>
                </el-form-item>
                <el-form-item label="状态" :label-width="formLabelWidth" prop="status">
                    <el-input v-model="courseDto.status" autocomplete="off"/>
                </el-form-item>
                <el-form-item label="报名数" :label-width="formLabelWidth" prop="enroll">
                    <el-input v-model="courseDto.enroll" autocomplete="off"/>
                </el-form-item>
                <el-form-item label="顺序" :label-width="formLabelWidth" prop="sort">
                    <el-input v-model="courseDto.sort" autocomplete="off"/>
                </el-form-item>
                <el-form-item label="逻辑删除" :label-width="formLabelWidth" prop="isDelete">
                    <el-input v-model="courseDto.isDelete" autocomplete="off"/>
                </el-form-item>
                <el-form-item label="讲师" :label-width="formLabelWidth" prop="teacherId">
                    <el-input v-model="courseDto.teacherId" autocomplete="off"/>
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
                <th>名称</th>
                <th>概述</th>
                <th>时长</th>
                <th>价格（元）</th>
                <th>封面</th>
                <th>级别</th>
                <th>收费</th>
                <th>状态</th>
                <th>报名数</th>
                <th>顺序</th>
                <th>创建时间</th>
                <th>修改时间</th>
                <th>讲师</th>
                <th>操作</th>
            </tr>
            </thead>

            <tbody>
            <tr v-for="course in courses">
                <th>{{ course.name}}</th>
                <th>{{ course.summary}}</th>
                <th>{{ course.time}}</th>
                <th>{{ course.price}}</th>
                <th>{{ course.image}}</th>
                <th>{{ course.level}}</th>
                <th>{{ course.charge}}</th>
                <th>{{ course.status}}</th>
                <th>{{ course.enroll}}</th>
                <th>{{ course.sort}}</th>
                <th>{{ course.createTime}}</th>
                <th>{{ course.editTime}}</th>
                <th>{{ course.teacherId}}</th>
                <td>
                    <div class="btn-group">
                        <el-tooltip class="item" effect="dark" content="更新" placement="top">
                            <el-button @click="get(course.id)"
                                       type="primary"
                                       icon="el-icon-edit"
                                       size="mini"/>
                        </el-tooltip>
                        <el-tooltip class="item" effect="dark" content="删除" placement="top">
                            <el-button @click="remove(course.id)"
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
        name: "course",
        data() {
            return {
                dialogFormVisible: false,
                formLabelWidth: '80px',
                title: "添加课程",
                courses: [], //数据显示列表
                total: 0,
                currentPage: 1,
                size: 10,
                courseDto: {},
                rules: {
                    name: [
                        {required: true, message: '请输入名称', trigger: 'blur'},
                    ],
                    summary: [
                        {required: true, message: '请输入概述', trigger: 'blur'},
                    ],
                    time: [
                        {required: true, message: '请输入时长', trigger: 'blur'},
                    ],
                    price: [
                        {required: true, message: '请输入价格（元）', trigger: 'blur'},
                    ],
                    image: [
                        {required: true, message: '请输入封面', trigger: 'blur'},
                    ],
                    level: [
                        {required: true, message: '请输入级别', trigger: 'blur'},
                    ],
                    charge: [
                        {required: true, message: '请输入收费', trigger: 'blur'},
                    ],
                    status: [
                        {required: true, message: '请输入状态', trigger: 'blur'},
                    ],
                    enroll: [
                        {required: true, message: '请输入报名数', trigger: 'blur'},
                    ],
                    sort: [
                        {required: true, message: '请输入顺序', trigger: 'blur'},
                    ],
                    isDelete: [
                        {required: true, message: '请输入逻辑删除', trigger: 'blur'},
                    ],
                    teacherId: [
                        {required: true, message: '请输入讲师', trigger: 'blur'},
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
                this.courseDto = {};
            },
            remove(id) {
                this.$confirm('此操作将永久删除该课程, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.$ajax
                        .get(process.env.VUE_APP_SERVER + "/api/service/course/delete/" + id)
                        .then((response) => {
                            this.list();
                            let result = response.data;
                            if (result.code === 200) {
                                this.msg('success', result.data);
                            } else {
                                this.msg('error', result.data);
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
                this.title = "修改课程信息";
                this.dialogFormVisible = true;
                //获取要更新的对象
                this.$ajax
                    .get(process.env.VUE_APP_SERVER + "/api/service/course/" + id)
                    .then((response) => {
                        let result = response.data;
                        this.courseDto = result.data;
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
                            .post(process.env.VUE_APP_SERVER + "/api/service/course/saveOrUpdate", this.courseDto)
                            .then((response) => {
                                let result = response.data;
                                this.courseDto.id = "";
                                this.msg('success', result.msg);
                                this.dialogFormVisible = false;
                                this.list();
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
                    .post(process.env.VUE_APP_SERVER + "/api/service/course/list", {
                        page: this.currentPage,
                        size: this.size
                    })
                    .then((response) => {
                        let result = response.data;
                        this.courses = result.data.list;
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

        },
    };
</script>

<style scoped>
    .el-input {
        width: 80% !important;
        margin: 0 5px !important;
    }

</style>