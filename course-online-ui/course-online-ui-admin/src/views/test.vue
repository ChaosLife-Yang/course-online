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

        {{info}}

        <el-table
                :data="tableData1"
                style="width: 100%"
                row-key="id"
                border
                highlight-current-row
                :tree-props="{children: 'children', hasChildren: 'hasChildren'}">
            <el-table-column
                    prop="date"
                    label="日期"
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="name"
                    label="姓名"
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="address"
                    label="地址">
            </el-table-column>
            <el-table-column
                    label="操作">
                <template slot-scope="scope">
                    <el-button type="primary" icon="el-icon-edit" size="mini" @click="get(scope.row)" circle></el-button>
                </template>
            </el-table-column>
        </el-table>
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
                tableData1: [{
                    id: 1,
                    date: '2016-05-02',
                    name: '王小虎',
                    address: '上海市普陀区金沙江路 1518 弄'
                }, {
                    id: 2,
                    date: '2016-05-04',
                    name: '王小虎',
                    address: '上海市普陀区金沙江路 1517 弄'
                }, {
                    id: 3,
                    date: '2016-05-01',
                    name: '王小虎',
                    address: '上海市普陀区金沙江路 1519 弄',
                    children: [{
                        id: 31,
                        date: '2016-05-01',
                        name: '王小虎',
                        address: '上海市普陀区金沙江路 1519 弄',
                        children: [{
                            id: 33,
                            date: '2016-05-01',
                            name: '王小虎',
                            address: '上海市普陀区金沙江路 1519 弄'
                        }]
                    }, {
                        id: 32,
                        date: '2016-05-01',
                        name: '王小虎',
                        address: '上海市普陀区金沙江路 1519 弄'
                    }]
                }, {
                    id: 4,
                    date: '2016-05-03',
                    name: '王小虎',
                    address: '上海市普陀区金沙江路 1516 弄'
                }]
            }
        },
        created() {
            this.decode();
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