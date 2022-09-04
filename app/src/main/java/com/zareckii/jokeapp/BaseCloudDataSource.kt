package com.zareckii.jokeapp

import retrofit2.Call
import retrofit2.Response
import java.net.UnknownHostException

class BaseCloudDataSource(private val service: JokeService) : CloudDataSource {

    override fun getJoke(callback: JokeCloudCallback) {
        service.getJoke().enqueue(object : retrofit2.Callback<JokeServerModel> {
            override fun onResponse(
                call: Call<JokeServerModel>,
                response: Response<JokeServerModel>
            ) {
                if (response.isSuccessful) {
                    callback.provide(response.body()!!.toJoke())
                } else {
                    callback.fail(ErrorType.SERVICE_UNAVAILABLE)
                }
            }

            override fun onFailure(call: Call<JokeServerModel>, t: Throwable) {
                val errorType = if (t is UnknownHostException) {
                    ErrorType.NO_CONNECTION
                } else {
                    ErrorType.SERVICE_UNAVAILABLE
                }
                callback.fail(errorType)
            }
        })
    }
}
