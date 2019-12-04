package com.example.kadesubmisidua.view.presenter

import com.example.kadesubmisidua.api.ApiRepository
import com.example.kadesubmisidua.api.SportDBApi
import com.example.kadesubmisidua.model.NextResponse
import com.example.kadesubmisidua.view._interface.DetailView
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class DetailPresenter(
    private val detailView: DetailView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {

    fun getDetailMatch(typeMatch : String, idEvent : String){
        detailView.showLoading()

        doAsync {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(SportDBApi.getMatch(typeMatch,idEvent)),
                NextResponse::class.java
            )

            uiThread {
                detailView.hideLoading()
                detailView.showDetailMatch(data.events)
            }
        }
    }
}