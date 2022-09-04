package com.zareckii.jokeapp

import com.google.gson.annotations.SerializedName

data class JokeServerModel(
    @SerializedName("id") private val id: Int,
    @SerializedName("type") private val type: String? = "",
    @SerializedName("setup") private val text: String? = "",
    @SerializedName("delivery") private val punchline: String? = ""
) {
    fun toJoke() = Joke(id, type, text, punchline)
}

