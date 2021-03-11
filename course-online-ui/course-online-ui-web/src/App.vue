<template>
    <div id="app">
        <bootHeader/>

        <router-view/>

        <bootFooter/>
    </div>
</template>

<script>
    import bootHeader from './components/boot-header'
    import bootFooter from './components/boot-footer'

    export default {
        name: 'App',
        components: {
            bootHeader,
            bootFooter
        }
    }
</script>

<style>
</style>
