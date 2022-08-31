package com.zareckii.jokeapp

interface Model {

    fun getJoke()

    fun init(callback: ResultCallback)

    fun clear()
}

interface ResultCallback {

    fun provideSuccess(data: Joke)

    fun provideError(error: JokeFailure)
}
