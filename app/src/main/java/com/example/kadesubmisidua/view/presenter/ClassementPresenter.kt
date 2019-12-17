package com.example.kadesubmisidua.view.presenter

import android.util.Log
import com.example.kadesubmisidua.api.ApiRepository
import com.example.kadesubmisidua.api.SportDBApi
import com.example.kadesubmisidua.model.classement.ClassementResponse
import com.example.kadesubmisidua.model.team.TeamsResponse
import com.example.kadesubmisidua.util.CoroutineContextProvider
import com.example.kadesubmisidua.view._interface.ClassementView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ClassementPresenter(
    private val classementView: ClassementView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getClassementList(typeMatch: String, idLeague: String) {
        classementView.showLoading()

        GlobalScope.launch ( context.main ){
            val data = gson.fromJson(
                apiRepository
                    .doRequest(SportDBApi.getClassement(typeMatch,idLeague)).await(),
                ClassementResponse::class.java
            )

            classementView.hideLoading()
            classementView.showClassementList(data.table)
        }
    }

//    fun getTeamThumb(typeMatch: String,idTeam : String){
//        Log.d("ClassementPresenter", "get team id "+idTeam + " typematch : "+typeMatch)
//        GlobalScope.launch(context.main)  {
//            val data = gson.fromJson(
//                apiRepository
//                    .doRequest(SportDBApi.getTeam(typeMatch,idTeam)).await(),
//                TeamsResponse::class.java
//            )
//            Log.d("ClassementPresenter", "get data "+data)
//            classementView.showTeamThumb(data.teams)
//
//        }
//    }
}