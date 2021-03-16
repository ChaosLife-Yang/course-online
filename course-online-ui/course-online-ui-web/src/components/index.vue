<template>
    <main role="main">

        <section class="jumbotron text-center">
            <div class="container">
                <h1>Album example1</h1>
                <p class="lead text-muted">Something short and leading about the collection below—its contents, the
                    creator, etc. Make it short and sweet, but not too short so folks don’t simply skip over it
                    entirely.</p>
                <p>
                    <a href="#" class="btn btn-primary my-2">Main call to action</a>
                    <a href="#" class="btn btn-secondary my-2">Secondary action</a>
                </p>
            </div>
        </section>

        <div class="album py-5 bg-light">
            <div class="container">

                <div class="row">
                    <a class="card" style="width: 18rem;">
                        <img src="" class="card-img-top" alt="...">
                        <div class="card-body">
                            <p class="card-text">Some quick example text to build on the card title and make up the bulk
                                of the card's content.</p>
                        </div>
                    </a>
                </div>

            </div>
        </div>

    </main>
</template>

<script>
    export default {
        name: "index",
        data() {
            return {}
        },
        methods: {}
    }
</script>

<style scoped>
    .col-md-3 {
        color: black;
    }
</style>