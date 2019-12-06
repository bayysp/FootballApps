package com.example.kadesubmisidua.view.presenter

import com.example.kadesubmisidua.api.ApiRepository
import com.example.kadesubmisidua.api.SportDBApi
import com.example.kadesubmisidua.model.nextmatch.NextResponse
import com.example.kadesubmisidua.model.previousmatch.PreviousResponse
import com.example.kadesubmisidua.model.team.TeamsResponse
import com.example.kadesubmisidua.view._interface.DetailPreviousView
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class DetailPreviousPresenter (
    private val detailView: DetailPreviousView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {

    fun getDetailMatch(typeMatch : String, idEvent : String){
        detailView.showLoading()

        doAsync {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(SportDBApi.getMatch(typeMatch,idEvent)),
                PreviousResponse::class.java
            )

            uiThread {
                detailView.hideLoading()
                detailView.showDetailMatch(data.events)
            }
        }
    }

    fun getDetailTeamHome(typeMatch: String,idTeam : String){
        detailView.showLoading()

        doAsync {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(SportDBApi.getTeam(typeMatch,idTeam)),
                TeamsResponse::class.java
            )

            uiThread {
                detailView.hideLoading()
                detailView.showDetailTeamHome(data.teams)
            }
        }
    }

    fun getDetailTeamAway(typeMatch: String,idTeam : String){
        detailView.showLoading()

        doAsync {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(SportDBApi.getTeam(typeMatch,idTeam)),
                TeamsResponse::class.java
            )

            uiThread {
                detailView.hideLoading()
                detailView.showDetailTeamAway(data.teams)
            }
        }
    }
}