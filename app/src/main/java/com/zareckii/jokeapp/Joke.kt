package com.zareckii.jokeapp

class Joke(
    private val id: Int,
    private val type: String? = "",
    private val text: String? = "",
    private val punchline: String? = "",
) {
    fun change(cacheDataSource: CacheDataSource) = cacheDataSource.addOrRemove(id, this)
    fun toBaseJoke() = BaseJokeUiModel(text?: "", punchline?: "")
    fun toFavoriteJoke() = FavoriteJokeUiModel(text?: "", punchline?: "")
    fun toJokeRealm(): JokeRealm {
        return JokeRealm().also {
            it.id = id
            it.type = type?: ""
            it.text = text?: ""
            it.punchline = punchline?: ""
        }
    }
}