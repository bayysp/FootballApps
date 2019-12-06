package com.example.kadesubmisidua.view.presenter

import com.example.kadesubmisidua.api.ApiRepository
import com.example.kadesubmisidua.api.SportDBApi
import com.example.kadesubmisidua.model.previousmatch.PreviousResponse
import com.example.kadesubmisidua.view._interface.PreviousMatchView
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class PreviousMatchPresenter(
    private val previousMatchView: PreviousMatchView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {

    fun getPreviousMatchList(typeMatch: String, idLeague: String) {
        previousMatchView.showLoading()

        doAsync {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(SportDBApi.getMatch(typeMatch, idLeague)),
                PreviousResponse::class.java
            )

            uiThread {
                previousMatchView.hideLoading()
                previousMatchView.showPreviousMatchList(data.events)
            }
        }
    }
}