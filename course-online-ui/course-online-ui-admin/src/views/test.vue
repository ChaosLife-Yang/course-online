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
        <el-image :fit="'scale-down'" style="width: 350px; height: 200px"
                  :src="require('@/assets/demo-course.jpg')"></el-image>

        <vod :before-upload="beforeAvatarUpload"
             :get-md5="getMd5"
             :upload-url="vod"
             :button-name="'点击上传'"
             :file-name="'测试'"
             @getVod="getVod"
        />
        {{vodName}}
        <ali-player :get-play-auth="vodAuth" :vod="'907322bd49dc4d51a9c0d06df8dcd016'">
        </ali-player>
    </div>
</template>

<script>
    import shardUpload from "../components/shardUpload";
    import vod from "../components/vod";
    import aliPlayer from "../components/aliPlayer";

    export default {
        name: "test",
        components: {shardUpload, vod, aliPlayer},
        data() {
            return {
                url: process.env.VUE_APP_SERVER + '/api/file/oss/upload',
                checkUrl: process.env.VUE_APP_SERVER + '/api/file/local/check',
                getMd5: process.env.VUE_APP_SERVER + '/api/file/local/getMd5',
                vod: process.env.VUE_APP_SERVER + '/api/file/oss/vod',
                vodAuth: process.env.VUE_APP_SERVER + '/api/file/oss/getPlayAuth',
                percentage: 0,
                success: "",
                fileUrl: "",
                vodName: '',
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
            },
            getVod(key) {
                this.vodName = key;
            }
        }

    }
</script>

<style scoped>

</style>