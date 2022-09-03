package com.zareckii.jokeapp

interface CacheDataSource {

    fun getJoke(jokeCachedCallback: JokeCachedCallback)

    fun addOrRemove(id: Int, joke: JokeServerModel) : Joke
}

interface JokeCachedCallback {

    fun provide(jokeServerModel: JokeServerModel)

    fun fail()
}
