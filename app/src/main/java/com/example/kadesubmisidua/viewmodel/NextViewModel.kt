package com.example.kadesubmisidua.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kadesubmisidua.api.ApiRepository
import com.example.kadesubmisidua.api.SportDBApi
import com.example.kadesubmisidua.model.NextItem
import com.example.kadesubmisidua.model.NextResponse
import com.example.kadesubmisidua.service.FootballService
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NextViewModel : ViewModel() {

    lateinit var idMatch: String
    lateinit var footballService: FootballService

    private val listNextMatch = MutableLiveData<ArrayList<NextItem>>()

    internal fun setNextMatch(idMatch: String) {
        this.idMatch = idMatch

//        var nextMatchItems = ArrayList<NextItem>()

        val call = footballService.getNextMatchApi()
        call.getNextMatch(idMatch).enqueue(object : Callback<NextResponse> {

            override fun onFailure(call: Call<NextResponse>, t: Throwable) {
                Log.d("NextViewModel", "error getting an data" + t.printStackTrace())
            }

            override fun onResponse(call: Call<NextResponse>, response: Response<NextResponse>) {
                if (response.code() == 200) {
                    val nextResponse: NextResponse? = response.body()


                    Log.d("NextViewModel", "success getting an data")

//                    val nextMatchItem : ArrayList<NextItem?>? = nextResponse?.events
//                    listNextMatch.postValue(nextMatchItem)

                }
            }

        })

    }

    internal fun getNextMatch(): LiveData<ArrayList<NextItem>> {
        return listNextMatch
    }

}

