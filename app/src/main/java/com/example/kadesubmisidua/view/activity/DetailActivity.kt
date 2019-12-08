package com.example.kadesubmisidua.view.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.kadesubmisidua.R
import com.example.kadesubmisidua.api.ApiRepository
import com.example.kadesubmisidua.model.nextmatch.NextItem
import com.example.kadesubmisidua.model.team.TeamsItem
import com.example.kadesubmisidua.view._interface.DetailView
import com.example.kadesubmisidua.view.presenter.DetailPresenter
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(), DetailView {

    private lateinit var detailPresenter: DetailPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

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
        activitydetail_tv_date.text = data.first().dateEvent
        activitydetail_tv_time.text = data.first().strTime
        activitydetail_tv_homename.text = data.first().strHomeTeam
        activitydetail_tv_awayname.text = data.first().strAwayTeam

        detailPresenter.getDetailTeamHome("lookupteam.php", data.first().idHomeTeam.toString())
        detailPresenter.getDetailTeamAway("lookupteam.php", data.first().idAwayTeam.toString())
    }

    override fun showDetailTeamHome(data: ArrayList<TeamsItem>) {

        Picasso.get().load(data.first().strTeamBadge).into(activitydetail_iv_homethumb)
    }

    override fun showDetailTeamAway(data: ArrayList<TeamsItem>) {
        Picasso.get().load(data.first().strTeamBadge).into(activitydetail_iv_awaythumb)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
        }
        return true
    }

}
