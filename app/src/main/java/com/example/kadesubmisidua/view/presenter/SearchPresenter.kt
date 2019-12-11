package com.example.kadesubmisidua.view.presenter

import com.example.kadesubmisidua.api.ApiRepository
import com.example.kadesubmisidua.api.SportDBApi
import com.example.kadesubmisidua.model.searchmatch.SearchResponse
import com.example.kadesubmisidua.util.CoroutineContextProvider
import com.example.kadesubmisidua.view._interface.SearchView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class SearchPresenter(
    private val searchView: SearchView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getSearchMatchList(typeMatch: String?, query: String?) {
        searchView.showLoading()

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(SportDBApi.getSearch(typeMatch, query)).await(),
                SearchResponse::class.java
            )

            if (data.event != null) {
                searchView.hideLoading()
                searchView.showSearchMatch(data.event)
            } else {
                searchView.showLoading()
            }

        }
    }
}