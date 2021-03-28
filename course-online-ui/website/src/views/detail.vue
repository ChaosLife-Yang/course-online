<template>
    <main role="main">
        <div class="album py-5 bg-light">
            <div class="container">
                <el-card class="box-card">
                    <el-row :gutter="20">
                        <el-col :xs="24" :sm="12" :md="12">
                            <el-image :fit="'contain'" style="width: 100%;" :src="course.image">
                            </el-image>
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
                                <el-button type="success" round>立即参加</el-button>
                            </p>
                        </el-col>
                    </el-row>
                </el-card>
                <el-row :gutter="20">
                    <el-col :xs="24" :sm="18" :md="18">
                        <el-card class="box-card">
                            <el-tabs v-model="activeName" type="card">
                                <el-tab-pane label="课程介绍" name="first">
                                    <div class="fr-wrapper" dir="ltr">
                                        <div class="fr-element fr-view" dir="ltr" aria-disabled="false"
                                             spellcheck="false">
                                            <p v-html="content.content"></p>
                                        </div>
                                    </div>
                                </el-tab-pane>
                                <el-tab-pane label="章节目录" name="second">

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
                                <div style="float: left;">
                                    <span>{{teacher.nickname}}</span><br>
                                    <p>{{teacher.position}}</p>
                                </div>
                            </el-row>
                            <p>{{teacher.intro}}</p>
                        </el-card>
                    </el-col>
                </el-row>

            </div>
        </div>
    </main>
</template>

<script>
    export default {
        name: "detail",
        data() {
            return {
                COURSE_LEVEL: this.$COURSE_LEVEL,
                COURSE_CHARGE: this.$COURSE_CHARGE,
                COURSE_STATUS: this.$COURSE_STATUS,
                activeName: 'first',
                course: {},
                teacher: {},
                content: {},
                chapter: []
            }
        },
        created() {
            this.init();
        },
        methods: {
            init() {
                if (this.$route.params && this.$route.params.id) {
                    //从路径获取id值
                    const id = this.$route.params.id;
                    //调用根据id查询的方法
                    this.getInfo(id);
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
                                        console.log(res.data);
                                        this.teacher = res.data;
                                    }
                                });
                        }
                    });
                this.$store.get(`${process.env.VUE_APP_SERVER}/api/front/display/courseContent/${id}`)
                    .then(response => {
                        if (response.data && response.data.code === 200) {
                            let result = response.data;
                            this.content = result.data;
                        }
                    })
            }
        }
    }
</script>

<style scoped>
    .el-card {
        margin-bottom: 20px;
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