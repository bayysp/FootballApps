package com.example.kadesubmisidua.view.presenter

import com.example.kadesubmisidua.TestContextProvider
import com.example.kadesubmisidua.api.ApiRepository
import com.example.kadesubmisidua.model.league.LeagueResponse
import com.example.kadesubmisidua.model.league.LeaguesItem
import com.example.kadesubmisidua.view._interface.DetailLeagueView
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class DetailLeaguePresenterTest {

    @Mock
    private lateinit var detailLeagueView : DetailLeagueView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var apiResponse : Deferred<String>

    private lateinit var detailLeaguePresenter: DetailLeaguePresenter

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        detailLeaguePresenter = DetailLeaguePresenter(detailLeagueView,apiRepository,gson,TestContextProvider())
    }

    @Test
    fun getDetailLeague() {
        val detailLeague : ArrayList<LeaguesItem> = arrayListOf()
        val response = LeagueResponse(detailLeague)
        val idLeague = "4346"
        val typeMatch = "lookupleague.php"

        runBlocking {
            Mockito.`when`(apiRepository.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")
            Mockito.`when`(
                gson.fromJson(
                    "",
                    LeagueResponse::class.java
                )
            ).thenReturn(response)

            detailLeaguePresenter.getDetailLeague(typeMatch,idLeague)

            Mockito.verify(detailLeagueView).showLoading()
            Mockito.verify(detailLeagueView).showDetailLeague(detailLeague)
            Mockito.verify(detailLeagueView).hideLoading()

        }
    }
}