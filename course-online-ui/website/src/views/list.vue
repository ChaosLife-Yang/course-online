<template>
    <main role="main">
        <div class="header-nav">
            <div class="clearfix">
                <div class="container">
                    <div class="row">
                        <div class="col-12">
                            <a v-on:click="onClickLevel1('0')" id="category-0" href="javascript:" class="cur">全部
                            </a>
                            <a v-for="o in level1" v-on:click="onClickLevel1(o.id)" :id="'category-' + o.id"
                               href="javascript:">
                                {{o.name}}
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="skill clearfix">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <a v-on:click="onClickLevel2('1')" id="category-1" href="javascript:" class="on">全部
                        </a>
                        <a v-for="o in level2" v-on:click="onClickLevel2(o.id)" :id="'category-' + o.id"
                           href="javascript:">
                            {{o.name}}
                        </a>

                        <div style="clear:both"></div>
                    </div>
                </div>
            </div>
        </div>

        <div class="album py-5 bg-light">
            <div class="container">
                <el-row :gutter="20">
                    <el-row :gutter="20">
                        <el-col :xs="12" :sm="8" :md="6" v-for="course in courses" :key="course.id">
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
                </el-row>

                <div class="container">
                    <el-pagination
                            background
                            @current-change="handleCurrentChange"
                            layout="prev, pager, next"
                            :page-size="40"
                            :pager-count="5"
                            :hide-on-single-page="true"
                            :total="total">
                    </el-pagination>
                </div>
            </div>

        </div>

    </main>
</template>

<script>
    export default {
        name: "list",
        data() {
            return {
                courses: [],
                level1: [],
                level2: [],
                level1Id: '',
                level2Id: '',
                total: 0
            };
        },
        created() {
            this.getCategory();
        },
        methods: {
            list(page = 1) {
                console.log(page, this.level1Id, this.level2Id);
                this.$store.post(`${process.env.VUE_APP_SERVER}/api/front/display/categoryCourse`,
                    {
                        level1: this.level1Id,
                        level2: this.level2Id,
                        page: page
                    }).then(response => {
                    if (response.data && response.data.code === 200) {
                        let result = response.data;
                        this.courses = result.data.list;
                        this.total = result.count;
                    }
                })
            },
            handleCurrentChange(val) {
                this.list(val);
            },
            allLevel2() {
                for (let i = 0; i < this.level1.length; i++) {
                    this.level2 = this.level2.concat(this.level1[i].children);
                }
            },
            getCategory() {
                this.$store.get(`${process.env.VUE_APP_SERVER}/api/front/display/category`)
                    .then(response => {
                        if (response.data && response.data.code === 200) {
                            let result = response.data;
                            this.level1 = result.data;
                            this.allLevel2();
                            // 点击一级分类时，显示激活状态
                            $("#category-0").siblings("a").removeClass("cur");
                            $("#category-0").addClass("cur");

                            // 点击一级分类时，二级分类【无限】按钮要设置激活状态
                            $("#category-1").siblings("a").removeClass("on");
                            $("#category-1").addClass("on");
                            this.list();
                        }
                    });
            },
            onClickLevel1(level1Id) {
                let _this = this;

                // 点击一级分类时，设置变量，用于课程筛选
                // 二级分类id为空，
                // 如果点击的是【全部】，则一级分类id为空
                _this.level2Id = null;
                _this.level1Id = level1Id;
                if (level1Id === "0") {
                    _this.level1Id = null;
                    this.allLevel2();
                }

                // 点击一级分类时，显示激活状态
                $("#category-" + level1Id).siblings("a").removeClass("cur");
                $("#category-" + level1Id).addClass("cur");

                // 点击一级分类时，二级分类【无限】按钮要设置激活状态
                $("#category-1").siblings("a").removeClass("on");
                $("#category-1").addClass("on");

                for (let i = 0; i < this.level1.length; i++) {
                    if (this.level1[i].id === level1Id) {
                        this.level2 = this.level1[i].children;
                    }
                }
                // 重新加载课程列表
                _this.list();
            },
            onClickLevel2(level2Id) {
                let _this = this;
                $("#category-" + level2Id).siblings("a").removeClass("on");
                $("#category-" + level2Id).addClass("on");

                // 点击二级分类时，设置变量，用于课程筛选
                if (level2Id === "1") {
                    _this.level2Id = null;
                } else {
                    _this.level2Id = level2Id;
                }

                // 重新加载课程列表
                _this.list();
            },
        }
    }
</script>

<style scoped>
    .name {
        width: 3em;
        color: #07111b;
        line-height: 32px;
        font-weight: 700;
        margin-right: 6px;
    }

    .el-card {
        margin-bottom: 20px;
    }

    a {
        text-decoration: none;
    }

    button {
        border: none;
        outline: none;
    }

    /* 头部 一级分类 */
    .header-nav {
        height: auto;
        background: #fff;
        box-shadow: 0 8px 16px 0 rgba(28, 31, 33, .1);
        padding: 16px 0;
        box-sizing: border-box;
        position: relative;
        z-index: 1;
        /*background-color: #d6e9c6;*/
    }

    .header-nav > div {
        width: 100%;
        padding-left: 12px;
        box-sizing: border-box;
        margin-left: auto;
        margin-right: auto;
        /*background-color: #B4D5AC;*/
    }

    .header-nav a {
        float: left;
        font-size: 16px;
        color: #07111b;
        line-height: 50px;
        height: 45px;
        position: relative;
        margin-right: 46px;
        font-weight: 700;
    }

    .header-nav a:hover {
        color: #c80;
    }

    .header-nav a.cur {
        color: #c80;
    }

    .header-nav a.cur:before {
        display: block;
    }

    .header-nav a:before {
        display: none;
        content: ' ';
        position: absolute;
        bottom: 0;
        background: #c80;
        width: 16px;
        height: 3px;
        left: 50%;
        margin-left: -8px;
    }

    /* 二级分类 */
    .skill {
        width: 100%;
        padding: 24px 0 0;
        position: relative;
        margin: 0 auto;
    }

    .skill a.on {
        color: #c80;
        background: rgba(204, 136, 0, .1);
    }

    .skill a {
        float: left;
        margin-right: 20px;
        padding: 0 12px;
        font-size: 14px;
        color: #4d555d;
        line-height: 32px;
        border-radius: 6px;
        margin-bottom: 12px;
    }

    .skill a:hover {
        background: #d9dde1;
    }
</style>