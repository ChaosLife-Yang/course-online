<template>
    <div>
        <el-upload
                :disabled="flag"
                id="el-up"
                class="upload-demo"
                action
                ref="upload"
                :http-request="upload"
                multiple
                :limit="1"
                :before-upload="beforeUpload"
                :show-file-list="false">
            <el-button :loading="flag" size="small" icon="el-icon-upload2" type="primary">{{buttonName}}</el-button>
        </el-upload>
    </div>
</template>

<script>
    export default {
        name: "vod",
        data() {
            return {
                flag: false,
            }
        },
        props: {
            buttonName: {
                type: String,
                required: true
            },
            getMd5: {
                type: String,
                required: true
            },
            uploadUrl: {
                type: String,
                required: true
            },
            fileName: {
                type: String,
                required: true
            },
            use: {
                type: String,
                default: 'C'
            },
            beforeUpload: Function
        },
        methods: {
            msg(type, message) {
                this.$message({
                    showClose: true,
                    type: type,
                    message: message
                });
            },
            upload(param) {
                this.flag = true;
                let file = param.file;
                let size = file.size;
                let name = file.name;
                //获取文件后缀
                let suffix = name.substring(name.lastIndexOf(".") + 1, name.length).toLowerCase();
                let fileName = this.fileName + "." + suffix;
                let shardSize = 10 * 1024 * 1024;
                let shardTotal = Math.ceil(size / shardSize);
                //获取文件最后的分片
                let index = (shardTotal - 1) * shardSize;
                let indexEnd = Math.min(file.size, index + shardSize);
                //将文件最后一片传上去获取md5
                let shard = file.slice(index, indexEnd);
                let md5 = new window.FormData();
                md5.append("file", shard);
                this.$ajax.post(this.getMd5, md5).then((res) => {
                    if (res.data.code === 200) {
                        //读取文件md5值
                        let key = res.data.data;
                        let formData = new window.FormData();
                        formData.append('file', file);
                        formData.append('suffix', suffix);
                        formData.append('shardSize', size);
                        formData.append('size', size);
                        formData.append('name', fileName);
                        formData.append('fileKey', key);
                        formData.append('useTo', this.use);
                        //上传视频
                        this.$ajax.post(this.uploadUrl, formData).then((response) => {
                            let resp = response.data;
                            if (resp.code == 200) {
                                this.msg('success', '上传成功');
                                this.$refs.upload.clearFiles();
                                this.flag = false;
                                this.$emit('getVod', resp.data);
                            }else {
                                this.$refs.upload.clearFiles();
                                this.flag = false;
                                this.msg('error', res.data.msg);
                            }
                        }).catch(error => {
                            this.$refs.upload.clearFiles();
                            this.flag = false;
                            this.msg('error', error);
                        });
                    } else {
                        this.$refs.upload.clearFiles();
                        this.flag = false;
                        this.msg('error', res.data.msg);
                    }
                }).catch(error => {
                    this.$refs.upload.clearFiles();
                    this.flag = false;
                    this.msg('error', error);
                });
            }
        }
    }
</script>

<style scoped>

</style>