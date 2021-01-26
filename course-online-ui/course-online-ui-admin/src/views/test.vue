<template>
    <div>
        <shard-upload
                :url="url"
                :check-url="checkUrl"
                :get-md5="getMd5"
                :button-name="'点击上传'"
                @changePercent="changePercent"
                @getUrl="getUrl">
        </shard-upload>
        <el-progress :percentage="percentage" :stroke-width="24"
                     :status="success"></el-progress>
        {{fileUrl}}
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
                getMd5: process.env.VUE_APP_SERVER + '/api/file/local/getMd5',
                percentage: 0,
                success: "",
                fileUrl: "",
            }
        },
        methods: {
            changePercent(percentage) {
                this.percentage = percentage;
                if (percentage == 100) {
                    this.success = 'success';
                } else {
                    this.success = '';
                }
            },
            getUrl(url) {
                this.fileUrl = url;
            }
        }

    }
</script>

<style scoped>

</style>