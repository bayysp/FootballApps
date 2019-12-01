package com.example.kadesubmisidua.service

import com.example.kadesubmisidua.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


class FootballService {

    fun getNextMatchApi(): NextMatchApi {

        val retrofit = Retrofit
            .Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(NextMatchApi::class.java)
    }
}