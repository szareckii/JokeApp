package com.zareckii.jokeapp

import io.realm.Realm
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BaseCachedDataSource(private val realmProvider: RealmProvider) : CacheDataSource {

    override suspend fun getJoke(): Result<Joke, Unit> {
        realmProvider.provider().use {
            val jokes = it.where(JokeRealm::class.java).findAll()
            if (jokes.isEmpty())
                return Result.Error(Unit)
            else
                jokes.random().let { joke ->
                    return Result.Success(
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

    override suspend fun addOrRemove(id: Int, joke: Joke): JokeUiModel =
        withContext(Dispatchers.IO) {
            realmProvider.provider().use {
                val jokeRealm = it.where(JokeRealm::class.java).equalTo("id", id).findFirst()
                return@withContext if (jokeRealm == null) {
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