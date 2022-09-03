package com.zareckii.jokeapp

import com.google.gson.annotations.SerializedName

data class JokeServerModel(
    @SerializedName("id") private val id: Int,
    @SerializedName("type") private val type: String? = "",
    @SerializedName("setup") private val text: String? = "",
    @SerializedName("delivery") private val punchline: String? = ""
) {
    fun toBaseJoke() = BaseJoke(text?: "", punchline?: "")
    fun toFavoriteJoke() = FavoriteJoke(text?: "", punchline?: "")

    fun change(cacheDataSource: CacheDataSource) = cacheDataSource.addOrRemove(id, this)

    fun toJokeRealm(): JokeRealm {
        return JokeRealm().also {
            it.id = id
            it.type = type?: ""
            it.text = text?: ""
            it.punchline = punchline?: ""
        }
    }
}

