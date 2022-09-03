package com.zareckii.jokeapp

import retrofit2.Call
import retrofit2.http.GET

interface JokeService {

//    @GET("https://official-joke-api.appspot.com/random_joke/")
//    @GET("https://api.publicapis.org/random")
    @GET("https://v2.jokeapi.dev/joke/Any")
    fun getJoke() : Call<JokeServerModel>
}