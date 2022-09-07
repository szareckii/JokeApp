package com.zareckii.jokeapp

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class JokeApp : Application() {

    lateinit var viewModel: ViewModel

    override fun onCreate() {
        super.onCreate()

        Realm.init(this)

        /*
        val configuration = RealmConfiguration.Builder()
//            .name("todo.db")
//            .deleteRealmIfMigrationNeeded()
//            .schemaVersion(0)
            .allowWritesOnUiThread(true)
            .allowQueriesOnUiThread(true)
            .build()

        Realm.setDefaultConfiguration(configuration)
*/
        fun client() =  OkHttpClient.Builder()
            .callTimeout(30, TimeUnit.SECONDS)
            .addNetworkInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.HEADERS })
            .build()


        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.googgle.com")
            .client(client())
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        viewModel = ViewModel(
            BaseModel(
//                TestCacheDataSource(),
//                TestCloudDataSource(),
                BaseCachedDataSource(BaseRealmProvider()),
                BaseCloudDataSource(retrofit.create(JokeService::class.java)),
                BaseResourceManager(this)
            )
        )
    }

}