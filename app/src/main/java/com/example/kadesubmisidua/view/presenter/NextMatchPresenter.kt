package com.example.kadesubmisidua.view.presenter

import com.example.kadesubmisidua.api.ApiRepository
import com.example.kadesubmisidua.api.SportDBApi
import com.example.kadesubmisidua.model.nextmatch.NextResponse
import com.example.kadesubmisidua.util.CoroutineContextProvider
import com.example.kadesubmisidua.view._interface.NextMatchView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class NextMatchPresenter(
    private val nextMatchView: NextMatchView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getNextMatchList(typeMatch: String, idLeague: String) {

        nextMatchView.showLoading()

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(SportDBApi.getMatch(typeMatch, idLeague)).await(),
                NextResponse::class.java
            )

            nextMatchView.hideLoading()
            nextMatchView.showNextMatchList(data.events)

        }
    }

}