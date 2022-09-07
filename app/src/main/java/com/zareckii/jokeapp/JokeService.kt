package com.zareckii.jokeapp

import retrofit2.Call
import retrofit2.http.GET

interface JokeService {

    @GET("https://v2.jokeapi.dev/joke/Any")
    suspend fun getJoke() : JokeServerModel
}