<template>
    <div>
        <shard-upload
                :url="url"
                :check-url="checkUrl"
                :get-md5="getMd5"
                :button-name="'点击上传'"
                :before-upload="beforeAvatarUpload"
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
            },
            beforeAvatarUpload(file) {
                let fileName = file.name;
                let suffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length).toLowerCase();
                const isLt2M = file.size / 1024 / 1024 < 2048;
                const right_type = ["avi", "wmv", "mpg", "mpeg", "mov", "rm", "ram", "swf", "flv", "mp4", "mp3", "wma", "avi", "rm", "rmvb", "flv", "mpg", "mkv"];
                let isVideo = right_type.find(item => item === suffix) !== undefined;
                if (!isVideo) {
                    this.$message.error('请上传视频!');
                }
                if (!isLt2M) {
                    this.$message.error('上传视频大小不能超过 2GB!');
                }
                return isVideo && isLt2M;
            }
        }

    }
</script>

<style scoped>

</style>