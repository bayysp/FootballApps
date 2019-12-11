package com.example.kadesubmisidua.view.presenter

import com.example.kadesubmisidua.TestContextProvider
import com.example.kadesubmisidua.api.ApiRepository
import com.example.kadesubmisidua.model.previousmatch.PreviousItem
import com.example.kadesubmisidua.model.previousmatch.PreviousResponse
import com.example.kadesubmisidua.view._interface.PreviousMatchView
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

class PreviousMatchPresenterTest {

    @Mock
    private lateinit var previousMatchView: PreviousMatchView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var apiResponse: Deferred<String>

    private lateinit var previousMatchPresenter: PreviousMatchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        previousMatchPresenter = PreviousMatchPresenter(
            previousMatchView, apiRepository, gson,
            TestContextProvider()
        )
    }

    @Test
    fun getPreviousMatchList() {
        //www.thesportsdb.com/api/v1/json/1/eventspastleague.php?id=4328
        val previousMatchItem: ArrayList<PreviousItem> = arrayListOf()
        val response = PreviousResponse(previousMatchItem)
        val idLeague = "4328"
        val typeMatch = "eventspastleague.php"

        runBlocking {
            Mockito.`when`(apiRepository.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            Mockito.`when`(
                apiResponse.await()
            ).thenReturn("")

            Mockito.`when`(gson.fromJson("", PreviousResponse::class.java)).thenReturn(response)

            previousMatchPresenter.getPreviousMatchList(typeMatch, idLeague)
            Mockito.verify(previousMatchView).showLoading()
            Mockito.verify(previousMatchView).showPreviousMatchList(previousMatchItem)
            Mockito.verify(previousMatchView).hideLoading()

        }
    }
}

//@Mock
//private lateinit var nextMatchView : NextMatchView
//
//@Mock
//private lateinit var gson: Gson
//
//@Mock
//private lateinit var apiRepository: ApiRepository
//
//@Mock
//private lateinit var apiResponse : Deferred<String>
//
//private lateinit var nextMatchPresenter: NextMatchPresenter
//
//@Before
//fun setUp(){
//    MockitoAnnotations.initMocks(this)
//    nextMatchPresenter = NextMatchPresenter(nextMatchView,apiRepository,gson,
//        TestContextProvider()
//    )
//}
//
//@Test
//fun getNextMatchList() {
//    //https://www.thesportsdb.com/api/v1/json/1/eventsnextleague.php?id=4328
//    val nextMatchItem : ArrayList<NextItem> = arrayListOf()
//    val response = NextResponse(nextMatchItem)
//    val idLeague = "4328"
//    val typeMatch = "eventsnextleague.php"
//
//    runBlocking {
//        Mockito.`when`(apiRepository.doRequest(ArgumentMatchers.anyString()))
//            .thenReturn(apiResponse)
//
//        Mockito.`when`(
//            apiResponse.await()).thenReturn("")
//
//        Mockito.`when`(gson.fromJson("",NextResponse::class.java)).thenReturn(response)
//
//        nextMatchPresenter.getNextMatchList(typeMatch,idLeague)
//        Mockito.verify(nextMatchView).showLoading()
//        Mockito.verify(nextMatchView).showNextMatchList()
//        Mockito.verify(nextMatchView).hideLoading()
//
//    }
//
//}