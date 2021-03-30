<template>
    <div class="prism-player" id="J_prismPlayer"></div>
</template>

<script>
    export default {
        name: "aliPlayer",
        props: {
            getPlayAuth: {
                type: String,
                require: true
            },
            vod: {
                type: String,
                require: true
            },
            width: {
                type: String,
                default: '90%'
            },
            height: {
                type: String,
                default: '400px'
            }
        },
        data() {
            return {
                player: {}
            }
        },
        created() {
            this.play();
        },
        methods: {
            play() {
                let formData = new window.FormData();
                formData.append("vod", this.vod);
                this.$ajax.post(this.getPlayAuth, formData).then(resp => {
                    let result = resp.data;
                    if (result.code === 200) {
                        //页面渲染之后
                        this.player = new Aliplayer({
                            id: 'J_prismPlayer',
                            vid: this.vod, // 视频id
                            playauth: result.data, // 播放凭证
                            encryptType: '1', // 如果播放加密视频，则需设置encryptType=1，非加密视频无需设置此项
                            width: this.width,
                            height: this.height,
                            // 以下可选设置
                            // cover: 'http://guli.shop/photo/banner/1525939573202.jpg', // 封面
                            qualitySort: 'asc', // 清晰度排序

                            mediaType: 'video', // 返回音频还是视频
                            autoplay: false, // 自动播放
                            isLive: false, // 直播
                            rePlay: false, // 循环播放
                            preload: true,
                            controlBarVisibility: 'hover', // 控制条的显示方式：鼠标悬停
                            useH5Prism: true, // 播放器类型：html5

                        }, function (player) {
                            console.log('播放器创建成功')
                        })
                    } else {
                        this.msg('error', result.msg);
                    }
                }).catch(err => {
                    console.log(err);
                })
            },
            msg(type, message) {
                this.$message({
                    showClose: true,
                    type: type,
                    message: message
                });
            },
        }
    }
</script>

<style scoped>

</style>