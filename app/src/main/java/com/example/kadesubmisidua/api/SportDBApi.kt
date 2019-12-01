package com.example.kadesubmisidua.api

import com.example.kadesubmisidua.BuildConfig

object SportDBApi {

    fun getMatch(typeMatch: String? , idLeague : String): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/"+typeMatch+"?id="+idLeague
    }

}