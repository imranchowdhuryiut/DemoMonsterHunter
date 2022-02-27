package com.example.demomonsterhunter.data.network

import com.example.demomonsterhunter.utills.Constants
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Imran Chowdhury on 2/26/2022.
 */

object ApiClient {

    val mInstance: Retrofit by lazy { createInstance() }

    private val gson by lazy { GsonBuilder().create() }

    private fun createInstance(): Retrofit {
        val client = OkHttpClient.Builder()
            .build()

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .build()
    }

    inline fun <reified T> createApiService(): T {
        return mInstance.create(T::class.java)
    }

}