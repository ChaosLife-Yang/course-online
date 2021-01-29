<template>
    <div>
        <froala :tag="'textarea'" :config="froalaConfig" v-model="model"></froala>
    </div>
</template>

<script>
    export default {
        name: "editor",
        props: {
            model: {
                type: String,
                require: true
            }
        },
        data() {
            return {
                froalaConfig: {
                    toolbarButtons: ['undo', 'redo', 'clearFormatting', '|', 'bold', 'italic', 'underline', 'strikeThrough', '|', 'fontFamily', 'fontSize',
                        'color', '|', 'paragraphFormat', 'align', 'outdent', 'indent', 'quote', '-', 'insertLink', 'insertImage',
                        'embedly', 'insertTable', '|', 'emoticons', 'specialCharacters', 'insertHR', 'selectAll', '|', 'spellChecker', 'help',
                        '|', 'fullscreen'],
                    // theme: "dark",//主题
                    placeholder: "请填写内容...",
                    language: "zh_cn",//国际化
                    imageUploadURL: this.gateway + "/api/file/oss/contentUpload",//上传图片url
                    imageAllowedTypes: ['jpeg', 'jpg', 'png'],
                    charCounterCount: false,
                    quickInsertButtons: ['image', 'table', 'ul', 'ol', 'hr'],//快速插入项
                    // toolbarVisibleWithoutSelection: true,//是否开启 不选中模式
                    // disableRightClick: true,//是否屏蔽右击
                    colorsHEXInput: false,//关闭16进制色值
                    toolbarSticky: false,//操作栏是否自动吸顶
                    codeMirror: true,
                    zIndex: 99999,
                    events: {
                        'froalaEditor.initialized': function () {
                            console.log('initialized')
                        }
                    },
                },
            }
        }
    }
</script>

<style scoped>

</style>