<template>
    <div>
        <input type="text" v-model="resource"><br/>
        <input type="text" v-model="resource"><br/>
        {{'permission: '+$hasPermission(resource,authorities)}}<br/>
        {{'menu permission: '+$hasMenuPermission(resource,authorities)}}
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
                info: {},
                resource: '',
                authorities:[],
            }
        },
        created() {
            let info = LocalStorage.get(USER_INFO) || {};
            this.authorities = info.authorities !== undefined ? info.authorities : [];
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
            },
            decode() {
                let token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiY2xpZW50Il0sInVzZXJfaWQiOjIsInVzZXJfbmFtZSI6InVzZXIiLCJzY29wZSI6WyJhcHAiXSwiZXhwIjoxNjEzMzU0OTU1LCJhdXRob3JpdGllcyI6WyJhZGRPcmRlciIsInNob3dPcmRlciJdLCJqdGkiOiJlNGQ3MmJhMS0xMjdjLTQ0OWUtODNkYi04MjJhOTRiYjAxNWIiLCJjbGllbnRfaWQiOiJjbGllbnQifQ.gXzZbxriIUrE4xssALHqmdyWL7aASBhPnHk5SJbLFTI";
                //截取token，获取载体
                let strings = token.split(".");
                //解析，需要吧‘_’,'-'进行转换否则会无法解析
                this.info = JSON.parse(decodeURIComponent(escape(window.atob(strings[1].replace(/-/g, "+").replace(/_/g, "/")))));
            },
            get(obj) {
                console.log(obj.id);
            }

        }

    }
</script>

<style scoped>

</style>