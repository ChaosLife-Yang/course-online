<template>
    <div>
        <el-button type="primary"
                   size="mini"
                   plain
                   @click="add">添加大章
        </el-button>
        <el-dialog :title="title" :visible.sync="dialogFormVisible">
            <el-form :model="chapterDto">
                <el-input v-model="chapterDto.id" style="display: none"/>
                <el-form-item label="大章名称" :label-width="formLabelWidth">
                    <el-input v-model="chapterDto.name" autocomplete="off"/>
                </el-form-item>
                <el-form-item label="所属课程" :label-width="formLabelWidth">
                    <el-input v-model="chapterDto.courseId" autocomplete="off"/>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="saveOrUpdate">确 定</el-button>
            </div>
        </el-dialog>
        <table id="simple-table" class="table table-bordered table-hover">
            <thead>
            <tr>
                <th>id</th>
                <th>名称</th>
                <th>课程id</th>
                <th>操作</th>
            </tr>
            </thead>

            <tbody>
            <tr v-for="chapter in chapters" :key="chapter.id">
                <td>{{ chapter.id }}</td>
                <td>{{ chapter.name }}</td>
                <td>{{ chapter.courseId }}</td>
                <td>
                    <div class="hidden-sm hidden-xs btn-group">
                        <button class="btn btn-xs btn-success">
                            <i class="ace-icon fa fa-check bigger-120"></i>
                        </button>

                        <button @click="update(chapter.id)" class="btn btn-xs btn-info">
                            <i class="ace-icon fa fa-pencil bigger-120"></i>
                        </button>

                        <button class="btn btn-xs btn-danger">
                            <i class="ace-icon fa fa-trash-o bigger-120"></i>
                        </button>

                        <button class="btn btn-xs btn-warning">
                            <i class="ace-icon fa fa-flag bigger-120"></i>
                        </button>
                    </div>

                    <div class="hidden-md hidden-lg">
                        <div class="inline pos-rel">
                            <button class="btn btn-minier btn-primary dropdown-toggle"
                                    data-toggle="dropdown"
                                    data-position="auto">
                                <i class="ace-icon fa fa-cog icon-only bigger-110"></i>
                            </button>

                            <ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
                                <li>
                                    <a href="#"
                                       class="tooltip-info"
                                       data-rel="tooltip"
                                       title="View">
                      <span class="blue">
                        <i class="ace-icon fa fa-search-plus bigger-120"></i>
                      </span>
                                    </a>
                                </li>

                                <li>
                                    <a @click="update(chapter.id)"
                                       class="tooltip-success"
                                       data-rel="tooltip"
                                       title="Edit">
                      <span class="green">
                        <i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
                      </span>
                                    </a>
                                </li>

                                <li>
                                    <a href="#"
                                       class="tooltip-error"
                                       data-rel="tooltip"
                                       title="Delete">
                      <span class="red">
                        <i class="ace-icon fa fa-trash-o bigger-120"></i>
                      </span>
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </td>
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
        name: "chapter",
        data() {
            return {
                dialogFormVisible: false,
                formLabelWidth: '80px',
                title: "添加大章",
                chapters: [], //数据显示列表
                total: 0,
                currentPage: 1,
                size: 10,
                chapterDto: {
                    id: "",
                    name: "",
                    courseId: ""
                }
            }
        },
        created() {
            this.list();
        },
        methods: {
            add() {
                this.dialogFormVisible = true;
                this.chapterDto.id = "";
                this.chapterDto.name = "";
                this.chapterDto.courseId = "";
            },
            update(id) {
                this.title = "修改大章信息";
                this.dialogFormVisible = true;
                //获取要更新的对象
                this.$ajax
                    .get(`http://127.0.0.1:9000/api/service/chapter/${id}`)
                    .then((response) => {
                        let result = response.data;
                        this.chapterDto = result.data;
                    })
                    .catch(error => {
                        this.$message({
                            showClose: true,
                            message: error,
                            type: 'error'
                        });
                    });
            },
            saveOrUpdate() {
                this.$ajax
                    .post("http://127.0.0.1:9000/api/service/chapter/saveOrUpdate", this.chapterDto)
                    .then((response) => {
                        let result = response.data;
                        this.chapterDto.id = "";
                        this.$message({
                            showClose: true,
                            message: result.msg,
                            type: 'success'
                        });
                        this.dialogFormVisible = false;
                        this.list();
                    })
                    .catch(error => {
                        this.$message({
                            showClose: true,
                            message: error,
                            type: 'error'
                        });
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
            //分页
            list() {
                this.$ajax
                    .post("http://127.0.0.1:9000/api/service/chapter/list", {
                        page: this.currentPage,
                        size: this.size
                    })
                    .then((response) => {
                        let result = response.data;
                        this.chapters = result.data.list;
                        this.total = result.count;
                    })
                    .catch(error => {
                        this.$message({
                            showClose: true,
                            message: error,
                            type: 'error'
                        });
                    });

            },

        },
    };
</script>

<style scoped>
    .el-input {
        width: 80% !important;
        margin: 0 5px !important;
    }
</style>