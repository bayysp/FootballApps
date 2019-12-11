package com.example.kadesubmisidua.view.presenter

import com.example.kadesubmisidua.api.ApiRepository
import com.example.kadesubmisidua.api.SportDBApi
import com.example.kadesubmisidua.model.nextmatch.NextResponse
import com.example.kadesubmisidua.model.team.TeamsResponse
import com.example.kadesubmisidua.util.CoroutineContextProvider
import com.example.kadesubmisidua.view._interface.DetailView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class DetailPresenter(
    private val detailView: DetailView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getDetailMatch(typeMatch: String, idEvent: String) {
        detailView.showLoading()

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(SportDBApi.getMatch(typeMatch, idEvent)).await(),
                NextResponse::class.java
            )

            detailView.hideLoading()
            detailView.showDetailMatch(data.events)

        }
    }

    fun getDetailTeamHome(typeMatch: String, idTeam: String) {
        detailView.showLoading()

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(SportDBApi.getTeam(typeMatch, idTeam)).await(),
                TeamsResponse::class.java
            )

            detailView.hideLoading()
            detailView.showDetailTeamHome(data.teams)

        }
    }

    fun getDetailTeamAway(typeMatch: String, idTeam: String) {
        detailView.showLoading()

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(SportDBApi.getTeam(typeMatch, idTeam)).await(),
                TeamsResponse::class.java
            )

            detailView.hideLoading()
            detailView.showDetailTeamAway(data.teams)

        }
    }
}