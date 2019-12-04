package com.example.kadesubmisidua.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.kadesubmisidua.R
import com.example.kadesubmisidua.api.ApiRepository
import com.example.kadesubmisidua.model.NextItem
import com.example.kadesubmisidua.view._interface.DetailView
import com.example.kadesubmisidua.view.presenter.DetailPresenter
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_detail_previous.*

class DetailPreviousActivity : AppCompatActivity(), DetailView {

    private lateinit var detailPresenter: DetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_previous)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val item = intent.getStringExtra("id_event")

        val request = ApiRepository()
        val gson = Gson()
        detailPresenter = DetailPresenter(this, request, gson)

        detailPresenter.getDetailMatch("lookupevent.php", item)
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showDetailMatch(data: ArrayList<NextItem>) {
        activitydetailprevious_tv_date.text = data.first().dateEvent
        activitydetailprevious_tv_time.text = data.first().strTime
        activitydetailprevious_tv_homename.text = data.first().strHomeTeam
        activitydetailprevious_tv_awayname.text = data.first().strAwayTeam
        activitydetailprevious_tv_homescore.text = data.first().intHomeScore.toString()
        activitydetailprevious_tv_awayscore.text = data.first().intAwayScore.toString()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
        }
        return true
    }
}
