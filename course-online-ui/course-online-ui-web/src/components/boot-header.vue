<template>
    <header>
        <el-backtop></el-backtop>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container">
                <a class="navbar-brand" href="javascript:">
                    <i class="fa fa-video-camera"></i>&nbsp;YUDI
                </a>
                <button class="navbar-toggler" type="button" data-toggle="collapse"
                        data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                        aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item active">
                            <a class="nav-link" href="javascript:">主页 <span class="sr-only">(current)</span></a>
                        </li>
                        <li class="nav-item active">
                            <a class="nav-link" href="javascript:">全部课程</a>
                        </li>
                    </ul>
                    <form class="form-inline my-2 my-lg-0" :action="baseUrl+'index'">
                        <input class="form-control mr-sm-2" name="words" type="search" placeholder="Search" aria-label="Search">
                    </form>
                    <button class="btn btn-dark">登录</button>
                    <button class="btn btn-outline-light">注册</button>
                </div>
            </div>

        </nav>
    </header>
</template>

<script>
    export default {
        name: "boot-header",
        data() {
            return {
                baseUrl: process.env.BASE_URL,
            }
        }
    }
</script>

<style scoped>

</style>