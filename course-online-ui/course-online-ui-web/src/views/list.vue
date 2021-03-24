<template>
    <main role="main">
        <div class="album py-5 bg-light">
            <div class="container">
                <span class="name">方向: </span>
                <el-button size="mini">全部</el-button>
                <el-button v-for="category in level1" :key="category.id" size="mini">{{ category.name}}</el-button>
            </div>
            <div class="container">
                <span class="name">分类: </span>
                <el-button size="mini">全部</el-button>
                <el-button v-for="category in level2" :key="category.id" size="mini">{{ category.name}}</el-button>
            </div>
        </div>

    </main>
</template>

<script>
    export default {
        name: "list",
        data() {
            return {
                level1: [],
                level2: []
            };
        },
        created() {
            this.getCategory();
        },
        methods: {
            getCategory() {
                this.$store.get(`${process.env.VUE_APP_SERVER}/api/front/display/category`)
                    .then(response => {
                        if (response.data && response.data.code === 200) {
                            let result = response.data;
                            this.level1 = result.data;
                            for (let i = 0; i < this.level1.length; i++) {
                                this.level2 = this.level2.concat(this.level1[i].children);
                            }
                        }
                    });
            },
            onLevel1(id) {
                for (let i = 0; i < this.level1.length; i++) {
                    if (this.level1[i].id === id) {
                        this.level2 = this.level1[i].children;
                    }
                }
            }

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
</style>