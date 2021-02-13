<template>
    <div>
        <el-button size="mini" plain>
            <router-link to="/business/chapter">
                <i class="ace-icon fa fa-arrow-left"></i>
                返回章节目录
            </router-link>
        </el-button>
        <h4 class="lighter">
            <i class="ace-icon fa fa-hand-o-right icon-animated-hand-pointer blue"></i>
            {{course.name}} / {{chapter.name}}
        </h4>
        <el-button type="primary"
                   size="mini"
                   plain
                   @click="add">添加小节
        </el-button>
        <el-dialog :title="title" :before-close="handleClose" :visible.sync="dialogFormVisible">
            <el-form :rules="rules" ref="ruleForm" :model="sectionDto">
                <el-input v-model="sectionDto.id" style="display: none"/>
                <el-form-item label="标题" :label-width="formLabelWidth" prop="title">
                    <el-input v-model="sectionDto.title" autocomplete="off"/>
                </el-form-item>
                <el-form-item label="课程" :label-width="formLabelWidth" prop="courseId">
                    {{course.name}}
                </el-form-item>
                <el-form-item label="大章" :label-width="formLabelWidth" prop="chapterId">
                    {{chapter.name}}
                </el-form-item>
                <el-form-item label="视频" :label-width="formLabelWidth" prop="video">
                    <vod :upload-url="gateway+'/api/file/oss/vod'"
                         :get-md5="gateway+'/api/file/local/getMd5'"
                         :button-name="'点击上传'"
                         :file-name="sectionDto.title"
                         @getVod="handleAvatarSuccess"
                         @getDuration="getDuration"
                         :before-upload="beforeAvatarUpload"/>
                    <ali-player v-if="sectionDto.vod" :get-play-auth="gateway+'/api/file/oss/getPlayAuth'" ref="player"
                                :vod="sectionDto.vod"/>
                </el-form-item>
                <el-form-item label="顺序" :label-width="formLabelWidth" prop="sort">
                    <el-input v-model="sectionDto.sort" autocomplete="off"/>
                </el-form-item>
                <el-form-item label="时长(秒)" :label-width="formLabelWidth" prop="time">
                    {{sectionDto.time}}
                </el-form-item>
                <el-form-item label="收费" :label-width="formLabelWidth" prop="charge">
                    <el-select v-model="sectionDto.charge" placeholder="请选择">
                        <el-option
                                v-for="item in charge"
                                :key="item.key"
                                :label="item.value"
                                :value="item.key">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="vod" :label-width="formLabelWidth" prop="vod">
                    {{sectionDto.vod}}
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button :loading="loading" type="primary" @click="saveOrUpdate('ruleForm')">确 定</el-button>
            </div>
        </el-dialog>
        <table id="simple-table" class="table table-bordered table-hover">
            <thead>
            <tr>
                <th>标题</th>
                <th>课程</th>
                <th>大章</th>
                <th>时长</th>
                <th>收费</th>
                <th>vod</th>
                <th>顺序</th>
                <th>操作</th>
            </tr>
            </thead>

            <tbody>
            <tr v-for="section in sections">
                <th>{{ section.title}}</th>
                <th>{{ course.name}}</th>
                <th>{{ chapter.name}}</th>
                <th>{{ section.time | formatSecond}}</th>
                <th>{{ charge | optionKV(section.charge)}}</th>
                <th>{{ section.vod}}</th>
                <th>{{ section.sort}}</th>
                <td>
                    <div class="btn-group">
                        <el-tooltip class="item" effect="dark" content="查看 & 更新" placement="top">
                            <el-button @click="get(section.id)"
                                       type="primary"
                                       icon="el-icon-edit"
                                       size="mini"/>
                        </el-tooltip>
                        <el-tooltip class="item" effect="dark" content="删除" placement="top">
                            <el-button @click="remove(section.id)"
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
    import vod from "../../components/vod";
    import aliPlayer from "../../components/aliPlayer";

    export default {
        name: "section",
        components: {vod, aliPlayer},
        data() {
            return {
                gateway: process.env.VUE_APP_SERVER,
                dialogFormVisible: false,
                loading: false,
                formLabelWidth: '80px',
                course: {},
                chapter: {},
                title: "添加小节",
                sections: [], //数据显示列表
                total: 0,
                currentPage: 1,
                size: 10,
                sectionDto: {},
                hackReset: true,
                charge: this.$SECTION_CHARGE,
                rules: {
                    title: [
                        {required: true, message: '请输入标题', trigger: 'blur'},
                    ],
                    charge: [
                        {required: true, message: '请输入收费', trigger: 'blur'},
                    ],
                    sort: [
                        {required: true, message: '请输入顺序', trigger: 'blur'},
                    ],
                },
            }
        },

        created() {
            this.course = SessionStorage.get(SESSION_KEY_COURSE) || {};
            if (Tool.isEmpty(this.course)) {
                this.$router.push("/business/course");
            }
            this.sectionDto.courseId = this.course.id;
            this.chapter = SessionStorage.get(SESSION_KEY_CHAPTER) || {};
            if (Tool.isEmpty(this.chapter)) {
                this.$router.push("/business/chapter");
            }
            this.sectionDto.chapterId = this.chapter.id;
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
                this.sectionDto = {};
                this.sectionDto.courseId = this.course.id;
                this.sectionDto.chapterId = this.chapter.id;
                this.percentage = 0;
            },
            remove(id) {
                this.$confirm('此操作将删除该小节, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.$ajax
                        .get(process.env.VUE_APP_SERVER + "/api/service/section/delete/" + id)
                        .then((response) => {
                            this.list();
                            let result = response.data;
                            if (result.code === 200) {
                                this.msg('success', result.msg);
                            } else {
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
                this.title = "修改小节信息";
                this.dialogFormVisible = true;
                this.percentage = 0;
                //获取要更新的对象
                this.$ajax
                    .get(process.env.VUE_APP_SERVER + "/api/service/section/" + id)
                    .then((response) => {
                        let result = response.data;
                        this.sectionDto = result.data;
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
                            .post(process.env.VUE_APP_SERVER + "/api/service/section/saveOrUpdate", this.sectionDto)
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
                                this.sectionDto.vod = '';
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
                    .post(process.env.VUE_APP_SERVER + "/api/service/section/list", {
                        courseId: this.sectionDto.courseId,
                        chapterId: this.sectionDto.chapterId,
                        page: this.currentPage,
                        size: this.size
                    })
                    .then((response) => {
                        let result = response.data;
                        this.sections = result.data.list;
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
                        this.sectionDto.vod = '';
                        done();
                    })
                    .catch(_ => {
                    });
            },
            handleAvatarSuccess(key) {
                this.sectionDto.vod = '';
                setTimeout(() => {
                    this.sectionDto.vod = key
                }, 500);
            },
            getDuration(key) {
                this.sectionDto.time = key;
            },
            beforeAvatarUpload: function (file) {
                let fileName = file.name;
                let suffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length).toLowerCase();
                const isLt2M = file.size / 1024 / 1024 < 2048;
                const right_type = ["avi", "wmv", "mpg", "mpeg", "mov", "rm", "ram", "swf", "flv", "mp4", "mp3", "wma", "avi", "rm", "rmvb", "flv", "mpg", "mkv"];
                let isVideo = right_type.find(item => item === suffix) !== undefined;
                if (!isVideo) {
                    this.$message.error('请上传视频 !');
                }
                if (!isLt2M) {
                    this.$message.error('上传视频大小不能超过 2GB !');
                }
                let blank = Tool.isEmpty(this.sectionDto.title);
                if (blank) {
                    this.$message.error('请输入小节名称');
                }
                return isVideo && isLt2M && !blank;
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