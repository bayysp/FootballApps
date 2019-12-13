package com.example.kadesubmisidua.api

import com.example.kadesubmisidua.BuildConfig

object SportDBApi {

    fun getMatch(typeMatch: String? , idLeague : String): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/"+typeMatch+"?id="+idLeague
    }

    fun getSearch(typeMatch: String?, query : String?) : String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/"+typeMatch+"?e="+query
    }

    //https://www.thesportsdb.com/api/v1/json/1/lookupteam.php?id={idTeam}
    fun getTeam(typeMatch: String?, idTeam : String?) : String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/"+typeMatch+"?id="+idTeam
    }

    fun getLeague(typeMatch: String? , idLeague : String?) : String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/"+typeMatch+"?id="+idLeague
    }

    //https://www.thesportsdb.com/api/v1/json/1/lookupevent.php?id=441613
    fun getEvent(typeMatch: String?, idEvent : String?) : String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/"+typeMatch+"?id="+idEvent
    }

//    https://www.thesportsdb.com/api/v1/json/1/lookuptable.php?l=4387
    fun getClassement(typeMatch : String?, idLeague: String?) : String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/"+typeMatch+"?id="+idLeague
    }
}