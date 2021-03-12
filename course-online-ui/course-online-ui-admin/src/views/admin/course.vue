<template>
    <div>
        <el-button type="primary"
                   size="mini"
                   plain
                   @click="add">添加课程
        </el-button>
        <el-dialog :before-close="handleClose" :title="title" :visible.sync="dialogFormVisible">
            <el-form :rules="rules" ref="ruleForm" :model="courseDto">
                <el-input v-model="courseDto.id" style="display: none"/>
                <el-form-item label="分类" :label-width="formLabelWidth">
                    <el-tree
                            :data="categoryList"
                            show-checkbox
                            node-key="id"
                            ref="tree"
                            highlight-current
                            :props="defaultProps">
                    </el-tree>
                </el-form-item>
                <el-form-item label="名称" :label-width="formLabelWidth" prop="name">
                    <el-input v-model="courseDto.name" autocomplete="off"/>
                </el-form-item>
                <el-form-item label="概述" :label-width="formLabelWidth" prop="summary">
                    <el-input type="textarea" v-model="courseDto.summary" autocomplete="off"/>
                </el-form-item>
                <el-form-item label="价格/元" :label-width="formLabelWidth" prop="price">
                    <el-input v-model="courseDto.price" autocomplete="off"/>
                </el-form-item>
                <el-form-item label="封面" :label-width="formLabelWidth" prop="image">
                    <shard-upload
                            :url="gateway+'/api/file/oss/upload'"
                            :check-url="gateway+'/api/file/local/check'"
                            :get-md5="gateway+'/api/file/local/getMd5'"
                            :button-name="'点击上传'"
                            :use="'C'"
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
                <el-form-item label="收费" :label-width="formLabelWidth" prop="charge">
                    <el-select v-model="courseDto.charge" placeholder="请选择">
                        <el-option
                                v-for="item in COURSE_CHARGE"
                                :key="item.key"
                                :label="item.value"
                                :value="item.key">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="等级" :label-width="formLabelWidth" prop="level">
                    <el-select v-model="courseDto.level" placeholder="请选择">
                        <el-option
                                v-for="item in COURSE_LEVEL"
                                :key="item.key"
                                :label="item.value"
                                :value="item.key">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="状态" :label-width="formLabelWidth" prop="status">
                    <el-select v-model="courseDto.status" placeholder="请选择">
                        <el-option
                                v-for="item in COURSE_STATUS"
                                :key="item.key"
                                :label="item.value"
                                :value="item.key">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="讲师" :label-width="formLabelWidth" prop="teacherId">
                    <el-select v-model="courseDto.teacherId" autocomplete="off">
                        <el-option
                                v-for="item in teachers"
                                :key="item.id"
                                :label="item.nickname"
                                :value="item.id">
                        </el-option>
                    </el-select>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button type="primary" @click="saveOrUpdate('ruleForm')">提 交</el-button>
            </div>
        </el-dialog>
        <el-dialog :before-close="handleClose" :title="contentTitle + '——' + title"
                   :visible.sync="dialogContentFormVisible">
            <div slot="footer" class="dialog-footer">
                <froala :tag="'textarea'" :config="froalaConfig" v-model="courseContentDto.content"></froala>
                <br/>
                <el-button type="primary" @click="addContent">提 交</el-button>
            </div>
        </el-dialog>
        <div class="row">
            <div v-for="course in courses" class="col-xs-6 col-sm-4 col-md-3">
                <div class="thumbnail search-thumbnail">
                    <el-image v-show="!course.image" :fit="'contain'" style="width: 100%; height: 50%"
                              :src="require('@/assets/demo-course.jpg')"/>
                    <el-image v-show="course.image" :fit="'contain'" style="width: 100%; height: 50%"
                              :src="course.image"/>
                    <div class="caption">
                        <div class="clearfix">
                        <span class="pull-right label label-danger info-label">
                            {{COURSE_LEVEL | optionKV(course.level)}}
                        </span>
                            <span class="pull-right label label-primary info-label">
                            {{COURSE_CHARGE | optionKV(course.charge)}}
                        </span>
                            <span class="pull-right label label-default info-label">
                            {{COURSE_STATUS | optionKV(course.status)}}
                        </span>
                        </div>

                        <h3 class="search-title">
                            <span class="blue c-title">{{ course.name }}</span>
                        </h3>
                        <span class="pull-right label label-grey info-label">
                            {{course.time | formatSecond}}
                        </span>
                        <p>
                            <span class="bigger-120">{{course.price}}&nbsp;<i class="fa fa-rmb"></i></span>&nbsp;
                        </p>
                        <p>
                            <el-button type="primary" @click="toChapter(course)" size="mini" plain>大章</el-button>
                            <el-button type="primary" @click="editContent(course)" size="mini" plain>内容</el-button>
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
                        </p>
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
        name: "course",
        components: {shardUpload},
        data() {
            return {
                gateway: process.env.VUE_APP_SERVER,
                percentage: 0,
                success: "",
                imageUrl: '',
                COURSE_LEVEL: this.$COURSE_LEVEL,
                COURSE_CHARGE: this.$COURSE_CHARGE,
                COURSE_STATUS: this.$COURSE_STATUS,
                dialogFormVisible: false,
                dialogContentFormVisible: false,
                formLabelWidth: '80px',
                title: "添加课程",
                courses: [], //数据显示列表
                total: 0,
                currentPage: 1,
                size: 10,
                courseDto: {},
                courseCategoryDto: {},
                contentTitle: "",
                courseContentDto: {id: "", content: ""},
                categoryList: [],
                teachers: [],
                defaultProps: {
                    children: 'children',
                    label: 'name'
                },
                froalaConfig: {
                    toolbarButtons: ['undo', 'redo', 'clearFormatting', 'bold', 'italic', 'underline', 'strikeThrough', 'fontFamily', 'fontSize', 'textColor', 'backgroundColor', 'paragraphFormat', 'align', 'formatOL', 'formatUL', 'quote', '-', 'insertLink', 'insertImage', 'embedly', 'insertTable', 'emoticons', 'specialCharacters', 'insertHR', 'selectAll', 'html', 'fullscreen', 'help'],
                    //['fullscreen', 'bold', 'italic', 'underline', 'strikeThrough', 'subscript', 'superscript', '|', 'fontFamily', 'fontSize', 'textColor', 'backgroundColor', 'inlineStyle', 'paragraphStyle', '|', 'paragraphFormat', 'align', 'formatOL', 'formatUL', 'outdent', 'indent', 'quote', '-', 'insertLink', 'insertImage', 'insertVideo', 'embedly', 'insertFile', 'insertTable', '|', 'emoticons', 'specialCharacters', 'insertHR', 'selectAll', 'clearFormatting', '|', 'print', 'spellChecker', 'help', 'html', '|', 'undo', 'redo'],//显示可操作项
                    theme: 'gray',
                    placeholder: "请填写内容...",
                    language: "zh_cn",//国际化
                    imageUploadURL: process.env.VUE_APP_SERVER + "/api/file/oss/contentUpload",//上传url
                    quickInsertButtons: ['image', 'table', 'ul', 'ol', 'hr'],//快速插入项
                    // toolbarVisibleWithoutSelection: true,//是否开启 不选中模式
                    // disableRightClick: true,//是否屏蔽右击
                    colorsHEXInput: false,//关闭16进制色值
                    toolbarSticky: false,//操作栏是否自动吸顶
                    zIndex: 99999,
                    events: {
                        'froalaEditor.initialized': function () {
                            console.log('froalaEditor initialized')
                        }
                    },
                },
                rules: {
                    name: [
                        {required: true, message: '请输入名称', trigger: 'blur'},
                    ],
                    summary: [
                        {required: true, message: '请输入概述', trigger: 'blur'},
                    ],
                    price: [
                        {required: true, message: '请输入价格（元）', trigger: 'blur'},
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
            toChapter(course) {
                SessionStorage.set(SESSION_KEY_COURSE, course);
                this.$router.push("/business/chapter");
            },
            editContent(course) {
                this.title = "课程内容介绍";
                this.dialogContentFormVisible = true;
                this.courseContentDto.id = course.id;
                this.contentTitle = course.name;
                this.$ajax
                    .get(process.env.VUE_APP_SERVER + "/api/service/courseContent/" + course.id)
                    .then((response) => {
                        let result = response.data;
                        if (result.data != null) {
                            this.courseContentDto.content = result.data.content;
                        } else {
                            this.courseContentDto.content = "";
                        }
                        if (result.code !== 200) {
                            this.msg('error', result.msg);
                        }
                    })
                ;
            },
            addContent() {
                this.$ajax
                    .post(process.env.VUE_APP_SERVER + "/api/service/courseContent/saveOrUpdate", this.courseContentDto)
                    .then((response) => {
                        let result = response.data;
                        if (result.code === 200) {
                            this.msg('success', result.msg);
                        } else {
                            this.msg('error', result.msg);
                        }
                    })
                ;
            },
            msg(type, message) {
                this.$message({
                    showClose: true,
                    type: type,
                    message: message
                });
            },
            add() {
                this.title = "添加课程";
                this.dialogFormVisible = true;
                this.courseDto = {};
                this.imageUrl = "";
                this.percentage = 0;
                this.$refs.tree.setCheckedKeys([]);
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
                this.title = "修改课程信息";
                this.dialogFormVisible = true;
                this.percentage = 0;
                //获取要更新的对象
                this.$ajax
                    .get(process.env.VUE_APP_SERVER + "/api/service/course/" + id)
                    .then((response) => {
                        let result = response.data;
                        this.courseDto = result.data;
                        if (this.courseDto.image != null) {
                            this.imageUrl = this.courseDto.image;
                        } else {
                            this.imageUrl = "";
                        }
                    })
                ;
                this.$ajax
                    .get(process.env.VUE_APP_SERVER + "/api/service/courseCategory/" + id)
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
                //添加课程
                this.$refs[formName].validate((valid) => {
                    if (Tool.isEmpty(this.$refs.tree.getCheckedKeys())) {
                        this.msg('error', "请选择分类");
                    }
                    if (valid) {
                        this.$ajax
                            .post(process.env.VUE_APP_SERVER + "/api/service/course/saveOrUpdate", this.courseDto)
                            .then((response1) => {
                                let courseId = response1.data.data;
                                //添加课程与分类关联
                                this.$ajax.post(process.env.VUE_APP_SERVER + "/api/service/courseCategory/saveOrUpdate",
                                    {
                                        courseId: courseId,
                                        categoryId: this.$refs.tree.getCheckedKeys()
                                    }).then((response2) => {
                                    let result = response2.data;
                                    if (result.code === 200) {
                                        this.msg('success', result.msg);
                                    } else {
                                        this.msg('error', result.msg);
                                    }
                                    this.dialogFormVisible = false;
                                    this.list();
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
                    .post(process.env.VUE_APP_SERVER + "/api/service/course/list", {
                        page: this.currentPage,
                        size: this.size
                    })
                    .then((response) => {
                        if (response.data != null) {
                            let result = response.data;
                            if (result.code === 200) {
                                this.courses = result.data.list;
                                this.total = result.count;
                            } else {
                                this.msg('error', result.msg);
                            }
                        }

                    })
                ;
                this.$ajax
                    .get(process.env.VUE_APP_SERVER + "/api/service/category/list")
                    .then((response) => {
                        if (response.data != null) {
                            let result = response.data;
                            if (result.code === 200) {
                                this.categoryList = result.data;
                            } else {
                                this.msg('error', result.msg);
                            }
                        }

                    })
                ;
                this.$ajax
                    .get(process.env.VUE_APP_SERVER + "/api/service/teacher/all")
                    .then((response) => {
                        if (response.data != null) {
                            let result = response.data;
                            if (result.code === 200) {
                                this.teachers = result.data;
                                this.total = result.count;
                            } else {
                                this.msg('error', result.msg);
                            }
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
                this.courseDto.image = url;
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
        },
    };
</script>

<style scoped>
    .el-input {
        width: 80% !important;
        margin: 0 5px !important;
    }

    .c-title {
        display: block;
        height: 40px;
        width: 100%;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
    }
</style>