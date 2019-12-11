package com.example.kadesubmisidua.view.presenter

import com.example.kadesubmisidua.TestContextProvider
import com.example.kadesubmisidua.api.ApiRepository
import com.example.kadesubmisidua.model.league.LeagueResponse
import com.example.kadesubmisidua.model.nextmatch.NextItem
import com.example.kadesubmisidua.model.nextmatch.NextResponse
import com.example.kadesubmisidua.model.team.TeamsItem
import com.example.kadesubmisidua.model.team.TeamsResponse
import com.example.kadesubmisidua.view._interface.DetailView
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class DetailPresenterTest {

    @Mock
    private lateinit var detailView: DetailView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var apiResponse: Deferred<String>

    private lateinit var detailPresenter: DetailPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        detailPresenter = DetailPresenter(
            detailView, apiRepository, gson,
            TestContextProvider()
        )
    }

    @Test
    fun getDetailMatch() {
        //lookupevent.php?id=441613

        val detailNextEvent: ArrayList<NextItem> = arrayListOf()
        val response = NextResponse(detailNextEvent)
        val idEvent = "441613"
        val typeMatch = "lookupevent.php"

        runBlocking {
            Mockito.`when`(apiRepository.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")
            Mockito.`when`(
                gson.fromJson(
                    "",
                    NextResponse::class.java
                )
            ).thenReturn(response)

            detailPresenter.getDetailMatch(typeMatch, idEvent)

            Mockito.verify(detailView).showLoading()
            Mockito.verify(detailView).showDetailMatch(detailNextEvent)
            Mockito.verify(detailView).hideLoading()

        }
    }

    @Test
    fun getDetailTeamHome() {
        //https://www.thesportsdb.com/api/v1/json/1/lookupteam.php?id=133604

        val detailTeamHome: ArrayList<TeamsItem> = arrayListOf()
        val response = TeamsResponse(detailTeamHome)
        val idTeam = "133604"
        val typeMatch = "lookupteam.php"

        runBlocking {
            Mockito.`when`(apiRepository.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")
            Mockito.`when`(
                gson.fromJson(
                    "",
                    TeamsResponse::class.java
                )
            ).thenReturn(response)

            detailPresenter.getDetailTeamHome(typeMatch, idTeam)

            Mockito.verify(detailView).showLoading()
            Mockito.verify(detailView).showDetailTeamHome(detailTeamHome)
            Mockito.verify(detailView).hideLoading()

        }
    }

    @Test
    fun getDetailTeamAway() {
        //https://www.thesportsdb.com/api/v1/json/1/lookupteam.php?id=133604

        val detailTeamHome: ArrayList<TeamsItem> = arrayListOf()
        val response = TeamsResponse(detailTeamHome)
        val idTeam = "133605"
        val typeMatch = "lookupteam.php"

        runBlocking {
            Mockito.`when`(apiRepository.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")
            Mockito.`when`(
                gson.fromJson(
                    "",
                    TeamsResponse::class.java
                )
            ).thenReturn(response)

            detailPresenter.getDetailTeamHome(typeMatch, idTeam)

            Mockito.verify(detailView).showLoading()
            Mockito.verify(detailView).showDetailTeamHome(detailTeamHome)
            Mockito.verify(detailView).hideLoading()
        }

    }

}