<template>
    <div>
        <el-upload
                :disabled="flag"
                id="el-up"
                class="avatar-uploader"
                action
                ref="upload"
                :http-request="upload"
                multiple
                :limit="1"
                :show-file-list="false">
            <el-button :loading="flag" size="small" type="primary">点击上传</el-button>
        </el-upload>
    </div>
</template>

<script>
    import BMF from 'browser-md5-file';

    export default {
        name: "shardUpload",
        data() {
            return {
                percentage: 0,
                flag: false
            }
        },
        props: {
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
            }
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
                let fileName = file.name;
                //一个分片10M
                let shardSize = this.shardSize;
                //获取文件后缀
                let suffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length).toLowerCase();

                const bmf = new BMF();
                bmf.md5(file, (err, md5) => {
                    //读取文件md5值
                    let key16 = md5;
                    this.$ajax.get(`${this.checkUrl}/${key16}`).then((response) => {
                        let result = response.data;
                        //分片索引
                        let shardIndex = result.data;
                        //获取分片总数
                        let shardTotal = Math.ceil(size / shardSize);
                        let percentage = shardIndex / shardTotal;
                        this.percentage = percentage.toFixed(3) * 100;
                        //向父组件传递百分比数据
                        this.$emit('changePercent', this.percentage);
                        //分片起始位置
                        let start = shardIndex * shardSize;
                        //分片结束位置
                        let end = Math.min(file.size, start + shardSize);
                        //截取分片数据
                        let fileShard = file.slice(start, end);
                        let newName = this.$uuid.v1() + "." + suffix;
                        //递归上传
                        this.shardUpload(file, start, end, fileShard, suffix, shardIndex, shardSize, shardTotal, size, fileName, newName, key16);

                    }).catch(error => {
                        this.msg('error', error);
                    });
                });

            },
            shardUpload(file, start, end, fileShard, suffix, shardIndex, shardSize, shardTotal, size, fileName, newName, key16) {
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
                    shardIndex++;
                    //获取进度
                    let percentage = shardIndex / shardTotal;
                    this.percentage = percentage.toFixed(3) * 100;
                    this.$emit('changePercent', this.percentage);
                    start = shardIndex * shardSize;
                    end = Math.min(file.size, start + shardSize);
                    fileShard = file.slice(start, end);
                    if (shardIndex < shardTotal) {
                        //递归上传
                        this.shardUpload(file, start, end, fileShard, suffix, shardIndex, shardSize, shardTotal, size, fileName, newName, key16);
                    } else {
                        this.$refs.upload.clearFiles();
                        this.flag = false;
                        if (resp.code != null && resp.code === 200) {
                            this.msg('success', "上传文件完成");
                            //向父组件传递文件路径
                            this.$emit('getUrl', resp.data);
                        }else {
                            this.msg('error', "上传文件有误");
                        }
                    }
                }).catch(error => {
                    this.$refs.upload.clearFiles();
                    this.msg('error', error);
                });
            }
        }
    }
</script>

<style scoped>

</style>