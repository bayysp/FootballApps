package com.example.kadesubmisidua.view.presenter

import com.example.kadesubmisidua.api.ApiRepository
import com.example.kadesubmisidua.api.SportDBApi
import com.example.kadesubmisidua.model.team.TeamsResponse
import com.example.kadesubmisidua.util.CoroutineContextProvider
import com.example.kadesubmisidua.view._interface.DetailTeamView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailTeamPresenter(
    private val detailTeamView: DetailTeamView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getDetailTeam(typeMatch : String, idTeam : String){
        detailTeamView.showLoading()

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository.doRequest(SportDBApi.getTeam(typeMatch,idTeam)).await(),
                TeamsResponse::class.java
            )

            detailTeamView.hideLoading()
            detailTeamView.showDetailTeam(data.teams)
        }
    }

}