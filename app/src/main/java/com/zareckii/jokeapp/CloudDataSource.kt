package com.zareckii.jokeapp

interface CloudDataSource {
    suspend fun getJoke(): Result<JokeServerModel, ErrorType>
}

enum class ErrorType{
    NO_CONNECTION,
    SERVICE_UNAVAILABLE
}