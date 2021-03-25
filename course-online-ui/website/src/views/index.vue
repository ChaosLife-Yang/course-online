<template>
    <main role="main">

        <el-carousel :interval="4000" :height="bannerHeight + 'px'" type="card">
            <el-carousel-item v-for="course in newCourse" :key="course.id">
                <router-link :to="'/detail/'+course.id">
                    <el-image v-if="course.image" style="width: 100%;height: 100%"
                              :src="course.image"/>
                    <el-image v-if="!course.image" style="width: 100%;height: 100%"
                              :src="require('@/assets/demo-course.jpg')"/>
                </router-link>
            </el-carousel-item>
        </el-carousel>

        <div class="album py-5 bg-light">
            <div class="container">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item active lead" aria-current="page">
                        新上好课
                    </li>
                </ol>
                <el-row :gutter="20">
                    <el-col :xs="12" :sm="8" :md="6" v-for="course in newCourse" :key="course.id">
                        <a href="#">
                            <el-card :body-style="{ padding: '0px' }" shadow="hover">
                                <el-image v-if="!course.image" :fit="'contain'" style="width: 100%; height: 50%"
                                          :src="require('@/assets/demo-course.jpg')"/>
                                <el-image v-if="course.image" :fit="'contain'" style="width: 100%; height: 50%"
                                          :src="course.image"/>
                                <div style="padding: 14px;">
                                    <span>{{course.name}}</span>
                                </div>
                            </el-card>
                        </a>
                    </el-col>
                </el-row>
            </div>
            <div class="container">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item active lead" aria-current="page">
                        热门课程
                    </li>
                </ol>
                <el-row :gutter="20">
                    <el-col :xs="12" :sm="8" :md="6" v-for="course in popular" :key="course.id">
                        <a href="#">
                            <el-card :body-style="{ padding: '0px' }" shadow="hover">
                                <el-image v-if="!course.image" :fit="'contain'" style="width: 100%; height: 50%"
                                          :src="require('@/assets/demo-course.jpg')"/>
                                <el-image v-if="course.image" :fit="'contain'" style="width: 100%; height: 50%"
                                          :src="course.image"/>
                                <div style="padding: 14px;">
                                    <span>{{course.name}}</span>
                                </div>
                            </el-card>
                        </a>
                    </el-col>
                </el-row>
            </div>
        </div>

    </main>
</template>

<script>
    export default {
        name: "index",
        data() {
            return {
                newCourse: [],
                bannerHeight: 400,
                popular: [],
            }
        },
        created() {
            this.list();
        },
        mounted() {
            // 首次加载时,需要调用一次
            this.screenWidth = window.innerWidth;
            this.setSize();
            // 窗口大小发生改变时,调用一次
            window.onresize = () => {
                this.screenWidth = window.innerWidth;
                this.setSize();
            }
        },
        methods: {
            setSize () {
                // 通过浏览器宽度(图片宽度)计算高度
                this.bannerHeight = 600 / 1920 * this.screenWidth;
            },
            list() {
                this.$store
                    .get(`${process.env.VUE_APP_SERVER}/api/front/display/coursePop`)
                    .then((response) => {
                        if (response.data && response.data.code === 200) {
                            let result = response.data;
                            console.log(result);
                            this.newCourse = result.data.fresh;
                            this.popular = result.data.popular;
                        }
                    });
            }
        }
    }
</script>

<style scoped>

    .el-card {
        margin-bottom: 20px;
    }

    a {
        text-decoration: none;
    }
</style>