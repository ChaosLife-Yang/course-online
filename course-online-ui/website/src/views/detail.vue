<template>
    <main role="main">
        <div class="album py-5 bg-light">
            <div class="container">
                <el-card class="box-card">
                    <el-row :gutter="20">
                        <el-col :xs="24" :sm="12" :md="12">
                            <el-image v-if="course.image" :fit="'contain'" style="width: 100%;"
                                      :src="course.image"/>
                            <el-image v-if="!course.image" :fit="'contain'" style="width: 100%;"
                                      :src="require('@/assets/demo-course.jpg')"/>
                        </el-col>
                        <el-col :xs="24" :sm="12" :md="12">
                            <h1>{{course.name}}</h1>
                            <p class="course-head-item">
                                <span><i class="fa fa-clock-o"></i> {{(course.time) | formatSecond}}</span>
                                <span>{{COURSE_LEVEL | optionKV(course.level)}}</span>
                                <span><i class="fa fa-user"></i> {{course.enroll}}</span>
                            </p>
                            <p class="course-head-desc">{{course.summary}}</p>
                            <p class="course-head-price">
                                <span class="price-now text-danger"><i class="fa fa-yen"></i>&nbsp;{{course.price}}&nbsp;&nbsp;</span>
                            </p>
                            <p class="course-head-button-links">
                                <el-button v-if="!join" @click="joinCourse" type="success" round>立即参加</el-button>
                                <span v-if="join"><i class="el-icon-check"></i>已参加</span>
                            </p>
                        </el-col>
                    </el-row>
                </el-card>
                <el-row :gutter="10">
                    <el-col :xs="24" :sm="18" :md="18">
                        <el-card class="box-card">
                            <el-tabs v-model="activeName">
                                <el-tab-pane label="课程介绍" name="first">
                                    <div class="fr-wrapper" dir="ltr">
                                        <div class="fr-element fr-view" dir="ltr" aria-disabled="false"
                                             spellcheck="false">
                                            <p v-html="content.content"></p>
                                        </div>
                                    </div>
                                </el-tab-pane>
                                <el-tab-pane label="章节目录" name="second">
                                    <ul style="font-family: 'Microsoft YaHei UI'" class="list-group list-group-flush">
                                        <li v-for="(c,i) in chapter" :key="c.id" class="list-group-item">
                                            <span style="font-weight: bold;font-size: 15px">{{c.name}}</span>
                                            <p style="font-size: 8px;color: grey;" v-if="c.sections">
                                                共{{c.sections.length}}节
                                                <el-button type="text" @click="doHide(c,i)" v-if="c.hide"
                                                           style="font-size: 8px;">展开
                                                </el-button>
                                                <el-button type="text" @click="doHide(c,i)" v-if="!c.hide"
                                                           style="font-size: 8px;">收起
                                                </el-button>
                                            </p>
                                            <ul v-if="c.sections&&(c.hide===false)" class="list-group list-group-flush">
                                                <li style="font-size: 15px" v-for="section in c.sections"
                                                    :key="section.id" class="list-group-item">
                                                    <i class="fa fa-video-camera"></i> {{section.title}}
                                                    ({{(section.time) | formatSecond}})
                                                    <el-button style="float: right" v-if="isPlay(section.charge)"
                                                               @click="play(section)" type="danger" size="mini"
                                                               plain round>播放
                                                    </el-button>
                                                </li>
                                            </ul>
                                        </li>
                                    </ul>
                                </el-tab-pane>
                            </el-tabs>
                        </el-card>
                    </el-col>
                    <el-col :xs="0" :sm="6" :md="6">
                        <el-card class="box-card">
                            <div slot="header" class="clearfix">
                                <span style="font-weight: bold">讲师</span>
                            </div>
                            <el-row :gutter="20">
                                <el-avatar :size="80" style="display: block;float: left;"
                                           :src="teacher.image"></el-avatar>
                                <div style="float: left;margin-left:1em;font-size: 14px;font-family: Consolas;">
                                    <p>{{teacher.nickname}}</p>
                                    <p>{{teacher.position}}</p>
                                </div>
                            </el-row>
                            <p>{{teacher.intro}}</p>
                        </el-card>
                    </el-col>
                </el-row>

            </div>
            <el-dialog :title="title" :before-close="handleClose" :visible.sync="dialogVisible">
                <div class="play-box">
                    <ali-player width="100%" height="100%" v-if="vod" :get-play-auth="gateway+'/api/file/oss/getPlayAuth'"
                                                  ref="player"
                                                  :vod="vod"/>
                </div>

            </el-dialog>
        </div>
    </main>
</template>

<script>
    import aliPlayer from "../components/aliPlayer";

    export default {
        name: "detail",
        components: {aliPlayer},
        data() {
            return {
                gateway: process.env.VUE_APP_SERVER,
                dialogVisible: false,
                title: '',
                COURSE_LEVEL: this.$COURSE_LEVEL_ARRAY,
                activeName: 'first',
                join: false,
                user: {},
                vod: '',
                course: {},
                teacher: {},
                content: {},
                chapter: []
            }
        },
        mounted() {
            this.init();
            this.$EventBus.$on('getUser', (data) => {
                this.user = data || {};
                if (Tool.isEmpty(this.user)) {
                    this.join = false;
                }
            })
        },
        methods: {
            init() {
                if (this.$route.params && this.$route.params.id) {
                    //从路径获取id值
                    const id = this.$route.params.id;
                    //调用根据id查询的方法
                    this.getInfo(id);
                    this.isJoin(id);
                } else {
                    this.$router.push("/list");
                }
            },
            isJoin(id) {
                this.user = LocalStorage.get(USER_INFO) || {};
                if (Tool.isNotEmpty(this.user)) {
                    this.$store.get(`${process.env.VUE_APP_SERVER}/api/front/memberCourse/course/${id}/${this.user.id}`, {
                        memberId: this.user.id,
                        courseId: this.$route.params.id
                    }).then(response => {
                        if (response.data && response.data.code === 200) {
                            this.join = true;
                        } else {
                            this.join = false;
                        }
                    });
                }
            },
            getInfo(id) {
                this.$store.get(`${process.env.VUE_APP_SERVER}/api/front/display/courseWeb/${id}`)
                    .then(response => {
                        if (response.data && response.data.code === 200) {
                            let result = response.data;
                            this.course = result.data.course;
                            this.chapter = result.data.chapterList;

                            this.$store.get(`${process.env.VUE_APP_SERVER}/api/front/display/teacher/${this.course.teacherId}`)
                                .then(resp => {
                                    if (resp.data && resp.data.code === 200) {
                                        let res = resp.data;
                                        this.teacher = res.data;
                                    }
                                });
                            for (let i = 0; i < this.chapter.length; i++) {
                                this.chapter[i].hide = true;
                            }
                        }
                    });
                this.$store.get(`${process.env.VUE_APP_SERVER}/api/front/display/courseContent/${id}`)
                    .then(response => {
                        if (response.data && response.data.code === 200) {
                            let result = response.data;
                            this.content = result.data;
                        }
                    })
            },
            //判断该小节是否可播放视频
            isPlay(charge) {
                //课程是免费的可播放
                if (this.course.charge === "F") {
                    return true;
                }
                //小节是免费的可播放
                if (charge === "F") {
                    return true;
                }
                //会员参加了此课也为true 否则为false
                return this.join;
            },
            doHide(c, i) {
                c.hide = !c.hide;
                // 只修改属性不起作用，需要$set
                this.$set(this.chapter, i, c);
            },
            joinCourse() {
                this.user = LocalStorage.get(USER_INFO) || {};
                if (Tool.isEmpty(this.user)) {
                    this.$router.push("/login");
                } else {
                    this.$store.post(`${process.env.VUE_APP_SERVER}/api/front/memberCourse/saveOrUpdate`, {
                        memberId: this.user.id,
                        courseId: this.$route.params.id
                    }).then(response => {
                        if (response.data && response.data.code === 200) {
                            this.join = true;
                            this.$notify({
                                title: '参加成功',
                                type: 'success'
                            });
                            this.course.enroll += 1;
                        } else {
                            this.join = false;
                        }
                    });
                }
            },
            handleClose(done) {
                this.$confirm('确认关闭？')
                    .then(_ => {
                        this.vod = '';
                        done();
                    })
                    .catch(_ => {
                    });
            },
            play(section) {
                this.dialogVisible = true;
                this.title = section.title;
                this.vod = section.vod;
                if (this.course.charge === "F" && section.charge === "F" && this.join === false && Tool.isNotEmpty(this.user)) {
                    this.joinCourse();
                }
            }
        }
    }
</script>

<style scoped>
    .el-card {
        margin-bottom: 20px;
    }

    .play-box {
        width: 100%;
        height: 500px;
        background: RGB(43, 43, 43);
    }

    h1 {
        font-size: 2rem;
        margin-bottom: 1.5rem;
    }

    .fr-wrapper {
        padding: 2rem;
    }

    .course-head-item span {
        margin-right: 1rem;
    }
</style>