package com.example.kadesubmisidua.view.presenter

import android.util.Log
import com.example.kadesubmisidua.api.ApiRepository
import com.example.kadesubmisidua.api.SportDBApi
import com.example.kadesubmisidua.model.team.TeamsResponse
import com.example.kadesubmisidua.util.CoroutineContextProvider
import com.example.kadesubmisidua.view._interface.TeamsView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TeamsPresenter(
    private val teamsView: TeamsView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context : CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getTeamsList(typeMatch : String, idLeague : String){
        teamsView.showLoading()


        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository.doRequest(
                    SportDBApi.getAllTeam(typeMatch,idLeague)
                ).await(), TeamsResponse::class.java
            )

            Log.d("TeamsPresenter", "data team : "+data)
            teamsView.hideLoading()
            teamsView.showTeamList(data.teams)
        }
    }
}