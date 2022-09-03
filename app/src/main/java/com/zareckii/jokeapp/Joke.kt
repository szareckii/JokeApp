package com.zareckii.jokeapp

import androidx.annotation.DrawableRes

class BaseJoke(text: String, punchline: String) : Joke(text, punchline) {
    override fun getIconResId() = R.drawable.ic_baseline_favorite_border_24
}

class FavoriteJoke(text: String, punchline: String) : Joke(text, punchline) {
    override fun getIconResId() = R.drawable.ic_baseline_favorite_24
}

class FailedJoke(text: String) : Joke(text, "") {
    override fun getIconResId() = 0
}

abstract class Joke(private val text: String, private val punchline: String) {

    private fun getJokeUi() = "$text\n$punchline"

    @DrawableRes
    protected abstract fun getIconResId() :Int

    fun map(callback: ViewModel.DataCallback) = callback.run {
        provideText(getJokeUi())
        provideIconRes(getIconResId())
    }
}