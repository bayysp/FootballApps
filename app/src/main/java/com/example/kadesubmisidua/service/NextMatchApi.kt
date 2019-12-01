package com.example.kadesubmisidua.service

import com.example.kadesubmisidua.BuildConfig
import com.example.kadesubmisidua.model.NextResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NextMatchApi {

    @GET("https://www.thesportsdb.com/api/v1/json/1/eventsnextleague.php")
    fun getNextMatch(@Query("id")idLeague : String) : Call<NextResponse>

}