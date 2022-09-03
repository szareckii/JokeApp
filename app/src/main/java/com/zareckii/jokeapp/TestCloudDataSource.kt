package com.zareckii.jokeapp

class TestCloudDataSource : CloudDataSource {
    private var count = 0
    override fun getJoke(callback: JokeCloudCallback) {
        val joke = JokeServerModel(
            count,
            "testType$count",
            "testText$count",
            "punchline$count",
        )
        callback.provide(joke)
        count++
    }
}