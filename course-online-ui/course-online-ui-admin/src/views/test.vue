<template>
    <div>
        <shardUpload
                :url="url"
                :check-url="checkUrl"
                @changePercent="changePercent">
        </shardUpload>
        <el-progress :percentage="percentage" :stroke-width="24"
                     :status="success"></el-progress>
    </div>
</template>

<script>
    import shardUpload from "../components/shardUpload";

    export default {
        name: "test",
        components: {shardUpload},
        data() {
            return {
                url: process.env.VUE_APP_SERVER + '/api/file/local/shardUpload',
                checkUrl: process.env.VUE_APP_SERVER + '/api/file/local/check',
                percentage: 0,
                success: ""
            }
        },
        methods: {
            changePercent(percentage) {
                this.percentage = percentage;
                if (percentage === 100) {
                    this.success = 'success';
                }else {
                    this.success = '';
                }
            }
        }

    }
</script>

<style scoped>

</style>