package com.example.kadesubmisidua.view.presenter

import com.example.kadesubmisidua.api.ApiRepository
import com.example.kadesubmisidua.api.SportDBApi
import com.example.kadesubmisidua.model.SearchResponse
import com.example.kadesubmisidua.view._interface.SearchView
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class SearchPresenter(
    private val searchView: SearchView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {
    fun getSearchMatchList(typeMatch : String?, query : String?){
        searchView.showLoading()

        doAsync {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(SportDBApi.getSearch(typeMatch,query)),
                SearchResponse::class.java
            )

            uiThread {
                if (data.event != null){
                    searchView.hideLoading()
                    searchView.showSearchMatch(data.event)
                }else{
                    searchView.showLoading()
                }
            }
        }
    }
}