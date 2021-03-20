<template>
    <main role="main">

        <el-carousel type="card">
            <el-carousel-item v-for="course in newCourse" :key="course.id">
                <el-image v-if="!course.image" style="width: 100%;height: 100%"
                          :src="require('@/assets/demo-course.jpg')"/>
                <el-image v-if="course.image" style="width: 100%;height: 100%"
                          :src="course.image"/>
            </el-carousel-item>
        </el-carousel>

        <div class="album py-5 bg-light">
            <div class="container">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item active lead" aria-current="page">
                        新上好课
                    </li>
                </ol>
            </div>
            <div class="container">
                <el-row :gutter="20">
                    <el-col :span="6" v-for="course in newCourse" :key="course.id">
                        <el-card :body-style="{ padding: '0px' }" shadow="hover">
                            <el-image v-show="!course.image" :fit="'contain'" style="width: 100%; height: 50%"
                                      :src="require('@/assets/demo-course.jpg')"/>
                            <el-image v-show="course.image" :fit="'contain'" style="width: 100%; height: 50%"
                                      :src="course.image"/>
                            <div style="padding: 14px;">
                                <span>{{course.name}}</span>
                            </div>
                        </el-card>
                    </el-col>
                </el-row>
            </div>
            <div class="container">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item active lead" aria-current="page">
                        热门课程
                    </li>
                </ol>
            </div>
            <div class="container">
                <el-row :gutter="20">
                    <el-col :span="6" v-for="course in popular" :key="course.id">
                        <el-card :body-style="{ padding: '0px' }" shadow="hover">
                            <el-image v-show="!course.image" :fit="'contain'" style="width: 100%; height: 50%"
                                      :src="require('@/assets/demo-course.jpg')"/>
                            <el-image v-show="course.image" :fit="'contain'" style="width: 100%; height: 50%"
                                      :src="course.image"/>
                            <div style="padding: 14px;">
                                <span>{{course.name}}</span>
                            </div>
                        </el-card>
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
                popular: [],
            }
        },
        created() {
            this.list();
        },

        methods: {
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


</style>