package com.zareckii.jokeapp

import androidx.annotation.DrawableRes

class BaseJokeUiModel(text: String, punchline: String) : JokeUiModel(text, punchline) {
    override fun getIconResId() = R.drawable.ic_baseline_favorite_border_24
}

class FavoriteJokeUiModel(text: String, punchline: String) : JokeUiModel(text, punchline) {
    override fun getIconResId() = R.drawable.ic_baseline_favorite_24
}

class FailedJokeUiModel(text: String) : JokeUiModel(text, "") {
    override fun getIconResId() = 0
}

abstract class JokeUiModel(private val text: String, private val punchline: String) {

    private fun getJokeUi() = "$text\n$punchline"

    @DrawableRes
    protected abstract fun getIconResId() :Int

    fun map(callback: ViewModel.DataCallback) = callback.run {
        provideText(getJokeUi())
        provideIconRes(getIconResId())
    }
}