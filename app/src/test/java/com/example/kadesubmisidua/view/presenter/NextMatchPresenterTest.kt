package com.example.kadesubmisidua.view.presenter

import com.example.kadesubmisidua.TestContextProvider
import com.example.kadesubmisidua.api.ApiRepository
import com.example.kadesubmisidua.model.nextmatch.NextItem
import com.example.kadesubmisidua.model.nextmatch.NextResponse
import com.example.kadesubmisidua.view._interface.NextMatchView
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class NextMatchPresenterTest {

    @Mock
    private lateinit var nextMatchView : NextMatchView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var apiResponse : Deferred<String>

    private lateinit var nextMatchPresenter: NextMatchPresenter

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        nextMatchPresenter = NextMatchPresenter(nextMatchView,apiRepository,gson,
            TestContextProvider()
        )
    }

    @Test
    fun getNextMatchList() {
        //https://www.thesportsdb.com/api/v1/json/1/eventsnextleague.php?id=4328
        val nextMatchItem : ArrayList<NextItem> = arrayListOf()
        val response = NextResponse(nextMatchItem)
        val idLeague = "4328"
        val typeMatch = "eventsnextleague.php"

        runBlocking {
            Mockito.`when`(apiRepository.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            Mockito.`when`(
                apiResponse.await()).thenReturn("")

            Mockito.`when`(gson.fromJson("",NextResponse::class.java)).thenReturn(response)

            nextMatchPresenter.getNextMatchList(typeMatch,idLeague)
            Mockito.verify(nextMatchView).showLoading()
            Mockito.verify(nextMatchView).showNextMatchList(nextMatchItem)
            Mockito.verify(nextMatchView).hideLoading()

        }

    }
}