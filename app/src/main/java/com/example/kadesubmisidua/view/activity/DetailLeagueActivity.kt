package com.example.kadesubmisidua.view.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.kadesubmisidua.R
import com.example.kadesubmisidua.api.ApiRepository
import com.example.kadesubmisidua.model.league.LeaguesItem
import com.example.kadesubmisidua.util.invisible
import com.example.kadesubmisidua.util.visible
import com.example.kadesubmisidua.view._interface.DetailLeagueView
import com.example.kadesubmisidua.view.presenter.DetailLeaguePresenter
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_league.*

class DetailLeagueActivity : AppCompatActivity() , DetailLeagueView {

    private lateinit var detailLeaguePresenter : DetailLeaguePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_league)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val request = ApiRepository()
        val gson = Gson()

        val idLeague = intent.getStringExtra("id_league")

        detailLeaguePresenter =DetailLeaguePresenter(this,request,gson)
        detailLeaguePresenter.getDetailLeague("lookupleague.php",idLeague)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
        }
        return true
    }

    override fun showLoading() {
        activitydetailleague_pb.visible()
    }

    override fun hideLoading() {
        activitydetailleague_pb.invisible()
    }

    override fun showDetailLeague(data: ArrayList<LeaguesItem>) {
        activitydetailleague_tv_leaguedesc.text = data.first().strDescriptionEN
        activitydetailleague_tv_leaguename.text = data.first().strLeague
        Picasso.get().load(data.first().strBadge.toString()).into(activitydetailleague_iv_leaguethumb)
    }

}
