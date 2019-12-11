package com.example.kadesubmisidua.view.presenter

import com.example.kadesubmisidua.api.ApiRepository
import com.example.kadesubmisidua.api.SportDBApi
import com.example.kadesubmisidua.model.league.LeagueResponse
import com.example.kadesubmisidua.util.CoroutineContextProvider
import com.example.kadesubmisidua.view._interface.DetailLeagueView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class DetailLeaguePresenter(
    private val detailLeagueView: DetailLeagueView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context : CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getDetailLeague(typeMatch: String, idLeague: String) {
        detailLeagueView.showLoading()

        GlobalScope.launch(context.main){
            val data = gson.fromJson(
                apiRepository
                    .doRequest(SportDBApi.getLeague(typeMatch, idLeague)).await(),
                LeagueResponse::class.java
            )

                detailLeagueView.hideLoading()
                detailLeagueView.showDetailLeague(data.leagues)


        }
    }
}