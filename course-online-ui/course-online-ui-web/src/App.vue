<template>
    <div id="app">
        <bootHeader/>

        <index/>

        <bootFooter/>
    </div>
</template>

<script>
    import bootHeader from './components/boot-header'
    import bootFooter from './components/boot-footer'
    import index from "./components/index";
    export default {
        name: 'App',
        components: {
            bootHeader,
            index,
            bootFooter
        }
    }
</script>

<style>
</style>
