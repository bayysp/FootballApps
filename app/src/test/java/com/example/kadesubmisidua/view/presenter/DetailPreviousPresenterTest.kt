package com.example.kadesubmisidua.view.presenter

import com.example.kadesubmisidua.TestContextProvider
import com.example.kadesubmisidua.api.ApiRepository
import com.example.kadesubmisidua.model.previousmatch.PreviousItem
import com.example.kadesubmisidua.model.previousmatch.PreviousResponse
import com.example.kadesubmisidua.model.team.TeamsItem
import com.example.kadesubmisidua.model.team.TeamsResponse
import com.example.kadesubmisidua.view._interface.DetailPreviousView
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class DetailPreviousPresenterTest {

    @Mock
    private lateinit var detailPreviousView: DetailPreviousView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var apiResponse: Deferred<String>

    private lateinit var detailPreviousPresenter: DetailPreviousPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        detailPreviousPresenter = DetailPreviousPresenter(
            detailPreviousView, apiRepository, gson,
            TestContextProvider()
        )
    }

    @Test
    fun getDetailMatch() {
        //lookupevent.php?id=441613

        val detailPreviousEvent: ArrayList<PreviousItem> = arrayListOf()
        val response = PreviousResponse(detailPreviousEvent)
        val idEvent = "441613"
        val typeMatch = "lookupevent.php"

        runBlocking {
            Mockito.`when`(apiRepository.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")
            Mockito.`when`(
                gson.fromJson(
                    "",
                    PreviousResponse::class.java
                )
            ).thenReturn(response)

            detailPreviousPresenter.getDetailMatch(typeMatch, idEvent)

            Mockito.verify(detailPreviousView).showLoading()
            Mockito.verify(detailPreviousView).showDetailMatch(detailPreviousEvent)
            Mockito.verify(detailPreviousView).hideLoading()

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

            detailPreviousPresenter.getDetailTeamHome(typeMatch, idTeam)

            Mockito.verify(detailPreviousView).showLoading()
            Mockito.verify(detailPreviousView).showDetailTeamHome(detailTeamHome)
            Mockito.verify(detailPreviousView).hideLoading()

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

            detailPreviousPresenter.getDetailTeamHome(typeMatch, idTeam)

            Mockito.verify(detailPreviousView).showLoading()
            Mockito.verify(detailPreviousView).showDetailTeamHome(detailTeamHome)
            Mockito.verify(detailPreviousView).hideLoading()

        }

    }
}