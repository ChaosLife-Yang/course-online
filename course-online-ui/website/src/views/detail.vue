<template>
    <main role="main">
    </main>
</template>

<script>
    export default {
        name: "detail",
        data() {
            return {
                course: {}
            }
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
                            this.course = result.data;
                        }
                    })
            }
        }
    }
</script>

<style scoped>

</style>