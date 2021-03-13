<template>
    <div>
        <el-button type="primary"
                   size="mini"
                   plain
                   @click="add">添加教师
        </el-button>
        <el-dialog :before-close="handleClose" :title="title" :visible.sync="dialogFormVisible">
            <el-form :rules="rules" ref="ruleForm" :model="teacherDto">
                <el-input v-model="teacherDto.id" style="display: none"/>
                <el-form-item label="姓名" :label-width="formLabelWidth" prop="name">
                    <el-input v-model="teacherDto.name" autocomplete="off"/>
                </el-form-item>
                <el-form-item label="昵称" :label-width="formLabelWidth" prop="nickname">
                    <el-input v-model="teacherDto.nickname" autocomplete="off"/>
                </el-form-item>
                <el-form-item label="头像" :label-width="formLabelWidth" prop="image">
                    <shard-upload
                            :url="gateway+'/api/file/oss/upload'"
                            :check-url="gateway+'/api/file/local/check'"
                            :get-md5="gateway+'/api/file/local/getMd5'"
                            :button-name="'点击上传'"
                            :use="'T'"
                            @changePercent="changePercent"
                            @getUrl="handleAvatarSuccess"
                            :before-upload="beforeAvatarUpload"/>
                    <el-progress :percentage="percentage" :status="success"></el-progress>
                    <el-image
                            v-if="imageUrl"
                            style="width: 100px; height: 100px"
                            :src="imageUrl"
                            :preview-src-list="[imageUrl]"
                            :fit="'scale-down'"></el-image>
                </el-form-item>
                <el-form-item label="职位" :label-width="formLabelWidth" prop="position">
                    <el-input v-model="teacherDto.position" autocomplete="off"/>
                </el-form-item>
                <el-form-item label="座右铭" :label-width="formLabelWidth" prop="motto">
                    <el-input type="textarea" v-model="teacherDto.motto" autocomplete="off"/>
                </el-form-item>
                <el-form-item label="简介" :label-width="formLabelWidth" prop="intro">
                    <el-input type="textarea" v-model="teacherDto.intro" autocomplete="off"/>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button type="primary" @click="saveOrUpdate('ruleForm')">提 交</el-button>
            </div>
        </el-dialog>
        <div class="row">
            <div v-for="teacher in teachers" class="col-md-3 col-sm-6">
                <div class="thumbnail search-thumbnail">
                    <span class="profile-picture">
                        <el-image v-show="!teacher.image" :fit="'scale-down'" style="width: 100%; height: 100%"
                                  :src="require('@/assets/讲师默认头像.jpg')" :title="teacher.intro"/>
                        <el-image v-show="teacher.image" :fit="'scale-down'" style="width: 100%; height: 100%"
                                  :src="teacher.image" :title="teacher.intro"/>
                    </span>
                    <div class="space-4"></div>
                    <span href="javascript:" class="label label-primary arrowed-in-right label-lg"
                          data-toggle="dropdown">
                        <span class="white">{{teacher.position}}</span>
                    </span>

                    <div class="space-6"></div>

                    <div class="text-center">
                    <span href="javascript:" class="text-info bigger-110" v-bind:title="teacher.motto">
                        <h4 class="lighter">
                            <i class="el-icon-user-solid"></i>{{teacher.nickname}}【{{teacher.name}}】</h4>
                    </span>
                    </div>

                    <div class="space-6"></div>

                    <div class="profile-social-links align-center">
                        <el-tooltip class="item" effect="dark" content="更新" placement="top">
                            <el-button @click="get(teacher.id)"
                                       type="primary"
                                       icon="el-icon-edit"
                                       size="mini"/>
                        </el-tooltip>
                        <el-tooltip class="item" effect="dark" content="删除" placement="top">
                            <el-button @click="remove(teacher.id)"
                                       type="danger"
                                       icon="el-icon-delete"
                                       size="mini"/>
                        </el-tooltip>
                    </div>

                </div>
            </div>
        </div>
        <div class="block">
            <el-pagination
                    background
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :current-page="currentPage"
                    :page-sizes="[8, 16, 24, 56, 104]"
                    :page-size="size"
                    :page-count="7"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="total"/>
        </div>
    </div>
</template>

<script>
    import shardUpload from "../../components/shardUpload";

    export default {
        name: "teacher",
        components: {shardUpload},
        data() {
            return {
                gateway: process.env.VUE_APP_SERVER,
                percentage: 0,
                success: "",
                dialogFormVisible: false,
                formLabelWidth: '80px',
                title: "添加教师",
                teachers: [], //数据显示列表
                total: 0,
                currentPage: 1,
                size: 10,
                imageUrl: '',
                teacherDto: {},
                rules: {
                    name: [
                        {required: true, message: '请输入姓名', trigger: 'blur'},
                    ],
                    nickname: [
                        {required: true, message: '请输入昵称', trigger: 'blur'},
                    ],
                    position: [
                        {required: true, message: '请输入职位', trigger: 'blur'},
                    ],
                    motto: [
                        {required: true, message: '请输入座右铭', trigger: 'blur'},
                    ],
                    intro: [
                        {required: true, message: '请输入简介', trigger: 'blur'},
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
                this.teacherDto = {};
                this.imageUrl = "";
                this.percentage = 0;
            },
            remove(id) {
                this.$confirm('此操作将删除该教师, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.$ajax
                        .get(process.env.VUE_APP_SERVER + "/api/service/teacher/delete/" + id)
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
                this.title = "修改教师信息";
                this.dialogFormVisible = true;
                this.percentage = 0;
                //获取要更新的对象
                this.$ajax
                    .get(process.env.VUE_APP_SERVER + "/api/service/teacher/info/" + id)
                    .then((response) => {
                        let result = response.data;
                        this.teacherDto = result.data;
                        if (this.teacherDto.image != null) {
                            this.imageUrl = this.teacherDto.image;
                        } else {
                            this.imageUrl = "";
                        }
                    })
                    ;
            },
            //添加或更新
            saveOrUpdate(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        this.$ajax
                            .post(process.env.VUE_APP_SERVER + "/api/service/teacher/saveOrUpdate", this.teacherDto)
                            .then((response) => {
                                let result = response.data;
                                if (result.code === 200) {
                                    this.msg('success', result.msg);
                                    this.dialogFormVisible = false;
                                    this.list();
                                } else {
                                    this.msg('error', result.msg);
                                    this.dialogFormVisible = true;
                                }
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
                    .post(process.env.VUE_APP_SERVER + "/api/service/teacher/list", {
                        page: this.currentPage,
                        size: this.size
                    })
                    .then((response) => {
                        if (response.data!=null){
                            let result = response.data;
                            this.teachers = result.data.list;
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
            },
            changePercent(percentage) {
                this.percentage = percentage;
                if (percentage == 100) {
                    this.success = 'success';
                } else {
                    this.success = '';
                }
            },
            handleAvatarSuccess(url) {
                this.imageUrl = url;
                this.teacherDto.image = url;
            },
            beforeAvatarUpload(file) {
                const isJPG = file.type === 'image/jpeg' || file.type === 'image/png';
                const isLt2M = file.size / 1024 / 1024 < 2;

                if (!isJPG) {
                    this.$message.error('上传头像图片只能是 JPG 或 PNG 格式!');
                }
                if (!isLt2M) {
                    this.$message.error('上传头像图片大小不能超过 2MB!');
                }
                return isJPG && isLt2M;
            }
        }

    };
</script>

<style scoped>

</style>