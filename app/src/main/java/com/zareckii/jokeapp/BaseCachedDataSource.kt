package com.zareckii.jokeapp

import io.realm.Realm

class BaseCachedDataSource(private val realm: Realm) : CacheDataSource {

    override fun getJoke(jokeCachedCallback: JokeCachedCallback) {
        realm.let {
            val jokes = it.where(JokeRealm::class.java).findAll()
            if (jokes.isEmpty())
                jokeCachedCallback.fail()
            else
                jokes.random().let { joke ->
                    jokeCachedCallback.provide(
                        Joke(
                            joke.id,
                            joke.type,
                            joke.text,
                            joke.punchline,
                        )
                    )
                }
        }
    }

    override fun addOrRemove(id: Int,  joke: Joke): JokeUiModel {
        realm.let {
            val jokeRealm = it.where(JokeRealm::class.java).equalTo("id", id).findFirst()
            return if (jokeRealm == null) {
                val newJoke = joke.toJokeRealm()
                it.executeTransaction { transition ->
                    transition.insert(newJoke)
                }
                joke.toFavoriteJoke()
            } else {
                it.executeTransaction {
                    jokeRealm.deleteFromRealm()
                }
                joke.toBaseJoke()
            }
        }
    }
}