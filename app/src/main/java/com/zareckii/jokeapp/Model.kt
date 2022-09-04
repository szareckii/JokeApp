package com.zareckii.jokeapp

interface Model {

    fun getJoke()

    fun init(callback: JokeCallback)

    fun clear()

    fun changeJokeStatus(jokeCallback: JokeCallback)

    fun chooseDataSource(cached: Boolean)

}

interface JokeCallback {

    fun provide(jokeUiModel: JokeUiModel)
}
