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
        </el-upload>&nbsp;
        <el-button type="info" class="upload-demo" size="small" :disabled="shut" @click="shut = true" plain>暂停
        </el-button>
        <el-button type="success" class="upload-demo" size="small" :disabled="carry" @click="upload(param)" plain>继续
        </el-button>
    </div>
</template>

<script>
    export default {
        name: "shardUpload",
        data() {
            return {
                percentage: 0,
                flag: false,
                shut: true,
                carry: true,
                param: {}
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
            url: {
                type: String,
                required: true
            },
            checkUrl: {
                type: String,
                required: true
            },
            use: {
                type: String,
                default: 'C'
            },
            shardSize: {
                type: Number,
                default: 10 * 1024 * 1024
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
                this.param = param;
                this.flag = true;
                this.shut = false;
                this.carry = true;
                let file = param.file;
                let size = file.size;
                let fileName = file.name;
                //一个分片10M
                let shardSize = this.shardSize;
                //获取文件后缀
                let suffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length).toLowerCase();
                let formData = new window.FormData();
                //获取分片总数
                let shardTotal = Math.ceil(size / shardSize);
                //获取文件最后的分片
                let index = (shardTotal - 1) * shardSize;
                let indexEnd = Math.min(file.size, index + shardSize);
                //将文件最后一片传上去获取md5
                let shard = file.slice(index, indexEnd);
                formData.append("file", shard);
                this.$ajax.post(this.getMd5, formData).then((res) => {
                    if (res.data.code === 200) {
                        //读取文件md5值
                        let key16 = res.data.data;
                        //获取分片索引
                        this.$ajax.get(`${this.checkUrl}/${key16}`).then((response) => {
                            let result = response.data;
                            let newName;
                            let percentage;
                            //分片索引
                            let shardIndex;
                            if (result.data.shardIndex != null) {
                                newName = result.data.newName;
                                shardIndex = result.data.shardIndex;
                                shardIndex += 1;
                            } else {
                                newName = this.$uuid.v4() + "." + suffix;
                                shardIndex = 0;
                            }
                            percentage = shardIndex / shardTotal;
                            //获得百分比
                            this.percentage = (percentage * 100).toFixed(0);
                            //向父组件传递百分比数据
                            this.$emit('changePercent', this.percentage);
                            //分片起始位置
                            let start = shardIndex * shardSize;
                            //分片结束位置
                            let end = Math.min(file.size, start + shardSize);
                            //截取分片数据
                            let fileShard = file.slice(start, end);

                            //递归上传
                            this.shardUpload(file, start, end, fileShard, suffix, shardIndex, shardSize, shardTotal, size, fileName, newName, key16);

                        }).catch(error => {
                            this.msg('error', error);
                            this.percentage = 0;
                            this.$emit('changePercent', this.percentage);
                            this.$refs.upload.clearFiles();
                            this.flag = false;
                        });
                    } else {
                        this.percentage = 0;
                        this.$refs.upload.clearFiles();
                        this.flag = false;
                        this.$emit('changePercent', this.percentage);
                        this.msg('error', res.data.msg);
                    }
                }).catch(error => {
                    this.percentage = 0;
                    this.$emit('changePercent', this.percentage);
                    this.$refs.upload.clearFiles();
                    this.flag = false;
                    this.msg('error', error);
                });

            },
            shardUpload(file, start, end, fileShard, suffix, shardIndex, shardSize, shardTotal, size, fileName, newName, key16) {
                if (this.shut) {
                    this.flag = false;
                    this.carry = false;
                    return
                }
                let formData = new window.FormData();
                formData.append('file', fileShard);
                formData.append('suffix', suffix);
                formData.append('shardIndex', shardIndex);
                formData.append('shardSize', shardSize);
                formData.append('shardTotal', shardTotal);
                formData.append('size', size);
                formData.append('name', fileName);
                formData.append('newName', newName);
                formData.append('fileKey', key16);
                formData.append('useTo', this.use);
                this.$ajax.post(this.url, formData).then((response) => {
                    let resp = response.data;
                    if (resp.code != 200) {
                        this.percentage = 0;
                        this.$emit('changePercent', this.percentage);
                        this.$refs.upload.clearFiles();
                        this.flag = false;
                        this.msg('error', '上传文件有误');
                        return
                    }
                    if (shardIndex < shardTotal) {
                        //文件分片
                        shardIndex++;
                        start = shardIndex * shardSize;
                        end = Math.min(file.size, start + shardSize);
                        fileShard = file.slice(start, end);
                        //获取进度
                        let percentage = shardIndex / shardTotal;
                        this.percentage = (percentage * 100).toFixed(0);
                        this.$emit('changePercent', this.percentage);
                        //递归上传
                        this.shardUpload(file, start, end, fileShard, suffix, shardIndex, shardSize, shardTotal, size, fileName, newName, key16);
                    } else {
                        this.$refs.upload.clearFiles();
                        this.flag = false;
                        if (resp.code != null && resp.code === 200) {
                            this.shut = true;
                            this.msg('success', "上传文件完成");
                            //向父组件传递文件路径
                            this.$emit('getUrl', resp.data);
                        } else {
                            this.msg('error', "上传文件有误");
                        }
                    }
                }).catch(error => {
                    this.percentage = 0;
                    this.$emit('changePercent', this.percentage);
                    this.$refs.upload.clearFiles();
                    this.flag = false;
                    this.msg('error', error);
                });

            }
        }
    }
</script>

<style>
    .el-input {
        width: 80% !important;
        margin: 0 5px !important;
    }

    #el-up input {
        display: none !important;
    }

    .avatar-uploader .el-upload {
        border: 1px dashed #d9d9d9;
        border-radius: 6px;
        cursor: pointer;
        position: relative;
        overflow: hidden;
    }

    .upload-demo {
        display: inline;
    }

    .avatar-uploader .el-upload:hover {
        border-color: #409EFF;
    }

    .avatar-uploader-icon {
        font-size: 28px;
        color: #8c939d;
        width: 178px;
        height: 103px;
        line-height: 178px;
        text-align: center;
        display: block;
        margin-top: 75px;
    }

    .avatar {
        width: 178px;
        height: 178px;
        display: block;
    }
</style>