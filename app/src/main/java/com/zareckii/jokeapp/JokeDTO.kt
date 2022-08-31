package com.zareckii.jokeapp

import com.google.gson.annotations.SerializedName

data class JokeDTO(
    @SerializedName("id") private val id: Int,
    @SerializedName("type") private val type: String,
    @SerializedName("text") private val text: String,
    @SerializedName("punchline") private val punchline: String
) {
    fun toJoke() = Joke(text, punchline)
}

