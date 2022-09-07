package com.zareckii.jokeapp

interface CacheDataSource {
    suspend fun getJoke(): Result<Joke, Unit>
    suspend fun addOrRemove(id: Int, joke: Joke) : JokeUiModel
}
