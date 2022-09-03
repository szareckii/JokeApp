package com.zareckii.jokeapp

import io.realm.RealmObject

open class JokeRealm : RealmObject() {
    var id: Int = -1
    var type: String = ""
    var text: String = ""
    var punchline: String = ""
}
