package com.example.kadesubmisidua.view.presenter

import com.example.kadesubmisidua.api.ApiRepository
import com.example.kadesubmisidua.api.SportDBApi
import com.example.kadesubmisidua.model.NextResponse
import com.example.kadesubmisidua.view._interface.NextMatchView
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class NextMatchPresenter(
    private val nextMatchView: NextMatchView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {

    fun getNextMatchList(typeMatch: String, idLeague: String) {

        nextMatchView.showLoading()

        doAsync {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(SportDBApi.getMatch(typeMatch, idLeague)),
                NextResponse::class.java
            )

            uiThread {
                nextMatchView.hideLoading()
                nextMatchView.showNextMatchList(data.events)
            }
        }
    }

}