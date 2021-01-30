<template>
    <div>
        <table id="simple-table" class="table table-bordered table-hover">
            <thead>
            <tr>
                <th>相对路径</th>
                <th>文件名</th>
                <th>后缀</th>
                <th>大小</th>
                <th>用途</th>
                <th>vod</th>
                <th>上传时间</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="file in files">
                <th>{{ file.path}}</th>
                <th>{{ file.name}}</th>
                <th>{{ file.suffix}}</th>
                <th>{{ file.size|formatFileSize}}</th>
                <th>{{ fileUse|optionKV(file.useTo)}}</th>
                <th>{{file.vod}}</th>
                <th>{{ file.createTime}}</th>
            </tr>
            </tbody>
        </table>
        <div class="block">
            <el-pagination
                    background
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :current-page="currentPage"
                    :page-sizes="[5,10, 20, 50, 100]"
                    :page-size="size"
                    :page-count="7"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="total"/>
        </div>
    </div>
</template>

<script>
    export default {
        name: "file",
        data() {
            return {
                dialogFormVisible: false,
                formLabelWidth: '80px',
                title: "添加文件",
                files: [], //数据显示列表
                total: 0,
                currentPage: 1,
                size: 10,
                fileDto: {},
                fileUse: this.$FILE_USE_ARRAY,
            }
        },

        created() {
            this.list();
        },
        methods: {
            msg(type, message) {
                this.$message({
                    showClose: true,
                    type: type,
                    message: message
                });
            },
            //分页
            list() {
                this.$ajax
                    .post(process.env.VUE_APP_SERVER + "/api/file/file/list", {
                        page: this.currentPage,
                        size: this.size
                    })
                    .then((response) => {
                        let result = response.data;
                        this.files = result.data.list;
                        this.total = result.count;
                    })
                    .catch(error => {
                        this.msg('error', error);
                    });

            },
            handleSizeChange(val) {
                this.size = val;
                this.list();
            },
            handleCurrentChange(val) {
                this.currentPage = val;
                this.list();
            },
            handleClose(done) {
                this.$confirm('确认关闭？')
                    .then(_ => {
                        done();
                    })
                    .catch(_ => {
                    });
            }

        },
    };
</script>

<style scoped>
    .el-input {
        width: 80% !important;
        margin: 0 5px !important;
    }

</style>