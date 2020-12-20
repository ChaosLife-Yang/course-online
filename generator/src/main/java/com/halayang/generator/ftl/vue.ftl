<template>
    <div>
        <el-button type="primary"
                   size="mini"
                   plain
                   @click="add">添加${moduleName}
        </el-button>
        <el-dialog :title="title" :visible.sync="dialogFormVisible">
            <el-form :rules="rules" ref="ruleForm" :model="${domain}Dto">
                <el-input v-model="${domain}Dto.id" style="display: none"/>
                <#list fieldList as field>
                <#if field.nameHump!="id" && field.nameHump!="createTime" && field.nameHump!="editTime">
                <el-form-item label="${field.nameCn}" :label-width="formLabelWidth" prop="${field.nameHump}">
                    <el-input v-model="${domain}Dto.${field.nameHump}" autocomplete="off"/>
                </el-form-item>
                </#if>
                </#list>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="saveOrUpdate('ruleForm')">确 定</el-button>
            </div>
        </el-dialog>
        <table id="simple-table" class="table table-bordered table-hover">
            <thead>
            <tr>
                <#list fieldList as field>
                <#if field.nameHump!="isDelete" && field.nameHump!="id">
                <th>${field.nameCn}</th>
                </#if>
                </#list>
                <th>操作</th>
            </tr>
            </thead>

            <tbody>
            <tr v-for="${domain} in ${domain}s">
                <#list fieldList as field>
                <#if field.nameHump!="isDelete" && field.nameHump!="id">
                <th>{{ ${domain}.${field.nameHump}}}</th>
                </#if>
                </#list>
                <td>
                    <div class="btn-group">
                        <el-tooltip class="item" effect="dark" content="更新" placement="top">
                            <el-button @click="get(${domain}.id)"
                                       type="primary"
                                       icon="el-icon-edit"
                                       size="mini"/>
                        </el-tooltip>
                        <el-tooltip class="item" effect="dark" content="删除" placement="top">
                            <el-button @click="remove(${domain}.id)"
                                       type="danger"
                                       icon="el-icon-delete"
                                       size="mini"/>
                        </el-tooltip>
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
        name: "${domain}",
        data() {
            return {
                dialogFormVisible: false,
                formLabelWidth: '80px',
                title: "添加${moduleName}",
                ${domain}s: [], //数据显示列表
                total: 0,
                currentPage: 1,
                size: 10,
                ${domain}Dto: {},
                rules: {
                    <#list fieldList as field>
                    <#if field.nameHump!="id" && field.nameHump!="createTime" && field.nameHump!="editTime">
                    ${field.nameHump}: [
                        {required: true, message: '请输入${field.nameCn}', trigger: 'blur'},
                    ],
                    </#if>
                    </#list>
                },
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
            add() {
                this.dialogFormVisible = true;
                this.${domain}Dto = {};
            },
            remove(id) {
                this.$confirm('此操作将删除该${moduleName}, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.$ajax
                        .get(process.env.VUE_APP_SERVER + "/api/service/${domain}/delete/" + id)
                        .then((response) => {
                            this.list();
                            let result = response.data;
                            if (result.code === 200) {
                                this.msg('success', result.data);
                            } else {
                                this.msg('error', result.data);
                            }
                        })
                        .catch(error => {
                            this.msg('error', error);
                        });
                }).catch(() => {
                    this.msg('info', '已取消删除');
                });

            },
            //获取信息
            get(id) {
                this.title = "修改${moduleName}信息";
                this.dialogFormVisible = true;
                //获取要更新的对象
                this.$ajax
                    .get(process.env.VUE_APP_SERVER + "/api/service/${domain}/" + id)
                    .then((response) => {
                        let result = response.data;
                        this.${domain}Dto = result.data;
                    })
                    .catch(error => {
                        this.msg('error', error);
                    });
            },
            //添加或更新
            saveOrUpdate(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        this.$ajax
                            .post(process.env.VUE_APP_SERVER + "/api/service/${domain}/saveOrUpdate", this.${domain}Dto)
                            .then((response) => {
                                let result = response.data;
                                this.${domain}Dto.id = "";
                                this.msg('success', result.msg);
                                this.dialogFormVisible = false;
                                this.list();
                            })
                            .catch(error => {
                                this.msg('error', error);
                            });
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });

            },

            //分页
            list() {
                this.$ajax
                    .post(process.env.VUE_APP_SERVER + "/api/service/${domain}/list", {
                        page: this.currentPage,
                        size: this.size
                    })
                    .then((response) => {
                        let result = response.data;
                        this.${domain}s = result.data.list;
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

        },
    };
</script>

<style scoped>
    .el-input {
        width: 80% !important;
        margin: 0 5px !important;
    }

</style>