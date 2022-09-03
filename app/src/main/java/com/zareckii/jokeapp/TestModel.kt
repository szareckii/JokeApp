package com.zareckii.jokeapp

class TestModel(resourceManager: ResourceManager) : Model {

    private var callback: JokeCallback? = null
    private var count = 0
    private val noConnection = NoConnection(resourceManager)
    private val serviceUnavailable = ServiceUnavailable(resourceManager)

    override fun getJoke() {
        Thread {
            Thread.sleep(500)
            when(count) {
                0 -> callback?.provide(BaseJoke("testText", "testPunchline"))
                1 -> callback?.provide(FavoriteJoke("favoriteJoke", "favorite joke punchline"))
                2 -> callback?.provide(FailedJoke(serviceUnavailable.getMessage()))
            }
            count++
            if (count == 3) count = 0
        }.start()
    }

    override fun init(callback: JokeCallback) {
        this.callback = callback
    }

    override fun clear() {
        callback = null
    }

    override fun changeJokeStatus(jokeCallback: JokeCallback) {}

    override fun chooseDataSource(favorites: Boolean) {}
}