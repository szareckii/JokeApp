package com.zareckii.jokeapp

import io.realm.Realm
import io.realm.com_zareckii_jokeapp_JokeRealmRealmProxy.insert

class BaseCachedDataSource(private val realm: Realm) : CacheDataSource {

    override fun getJoke(jokeCachedCallback: JokeCachedCallback) {
        realm.let {
//            it.executeTransactionAsync {
                val jokes = it.where(JokeRealm::class.java).findAll()
                if (jokes.isEmpty())
                    jokeCachedCallback.fail()
                else
                    jokes.random().let { joke ->
                        jokeCachedCallback.provide(
                            JokeServerModel(
                                joke.id,
                                joke.type,
                                joke.text,
                                joke.punchline,
                            )
                        )
                    }
//            }
        }
    }

    override fun addOrRemove(id: Int, jokeServerModel: JokeServerModel): Joke {
        realm.let {
//            it.executeTransactionAsync {
                val jokeRealm = it.where(JokeRealm::class.java).equalTo("id", id).findFirst()
                if (jokeRealm == null) {
                    val newJoke = jokeServerModel.toJokeRealm()
                    it.executeTransactionAsync { transition ->
                        transition.insert(newJoke)
                    }
                    jokeServerModel.toFavoriteJoke()
                } else {
                    it.executeTransactionAsync {
                        jokeRealm.deleteFromRealm()
                    }
                    jokeServerModel.toBaseJoke()
                }
            }
            return jokeServerModel.toFavoriteJoke()
//        }
    }
}