<template>
    <div>
        <shardUpload
                :url="url"
                :check-url="checkUrl"/>

    </div>
</template>

<script>
    import shardUpload from "../components/shardUpload";

    export default {
        name: "test",
        components: {shardUpload},
        data() {
            return {
                url: process.env.VUE_APP_SERVER + '/api/file/local/shardUpload',
                checkUrl: process.env.VUE_APP_SERVER + '/api/file/local/check',
            }
        }

    }
</script>

<style scoped>

</style>