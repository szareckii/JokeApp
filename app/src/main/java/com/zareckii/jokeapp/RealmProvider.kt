package com.zareckii.jokeapp

import io.realm.Realm

interface RealmProvider {
    fun provider(): Realm
}

class BaseRealmProvider : RealmProvider {
    override fun provider(): Realm = Realm.getDefaultInstance()
}