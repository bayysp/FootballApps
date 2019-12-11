package com.example.kadesubmisidua.view.presenter

import com.example.kadesubmisidua.TestContextProvider
import com.example.kadesubmisidua.api.ApiRepository
import com.example.kadesubmisidua.model.searchmatch.SearchItem
import com.example.kadesubmisidua.model.searchmatch.SearchResponse
import com.example.kadesubmisidua.view._interface.SearchView
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

class SearchPresenterTest {

    @Mock
    private lateinit var searchView : SearchView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var apiResponse : Deferred<String>

    private lateinit var searchPresenter: SearchPresenter

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        searchPresenter = SearchPresenter(searchView,apiRepository,gson,
            TestContextProvider()
        )
    }

    @Test
    fun getSearchMatchList() {
        //https://www.thesportsdb.com/api/v1/json/1/searchevents.php?e=Arsenal_vs_Chelsea
        val searchItem : ArrayList<SearchItem> = arrayListOf()
        val response = SearchResponse(searchItem)
        val query = "Liverpool"
        val typeMatch = "searchevents.php"

        runBlocking {
            Mockito.`when`(apiRepository.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            Mockito.`when`(
                apiResponse.await()).thenReturn("")

            Mockito.`when`(gson.fromJson("",SearchResponse::class.java)).thenReturn(response)

            searchPresenter.getSearchMatchList(typeMatch,query)
            Mockito.verify(searchView).showLoading()
            Mockito.verify(searchView).showSearchMatch(searchItem)
            Mockito.verify(searchView).hideLoading()

        }
    }
}