package com.zareckii.jokeapp

interface CloudDataSource {

    fun getJoke(callback: JokeCloudCallback)
}

interface JokeCloudCallback {

//    fun provide(joke: JokeServerModel)
    fun provide(joke: JokeServerModel)
    fun fail(error: ErrorType)
}

enum class ErrorType{
    NO_CONNECTION,
    SERVICE_UNAVAILABLE
}