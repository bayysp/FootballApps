package com.example.kadesubmisidua.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.kadesubmisidua.R
import com.example.kadesubmisidua.api.ApiRepository
import com.example.kadesubmisidua.model.nextmatch.NextItem
import com.example.kadesubmisidua.model.previousmatch.PreviousItem
import com.example.kadesubmisidua.model.team.TeamsItem
import com.example.kadesubmisidua.view._interface.DetailPreviousView
import com.example.kadesubmisidua.view._interface.DetailView
import com.example.kadesubmisidua.view.presenter.DetailPresenter
import com.example.kadesubmisidua.view.presenter.DetailPreviousPresenter
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_previous.*
import kotlinx.android.synthetic.main.item_nextmatch.*

class DetailPreviousActivity : AppCompatActivity(), DetailPreviousView {

    private lateinit var detailPreviousPresenter: DetailPreviousPresenter

    private var idTeamHome: String? = ""
    private var idTeamAway: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_previous)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val item = intent.getStringExtra("id_event")

        val request = ApiRepository()
        val gson = Gson()
        detailPreviousPresenter = DetailPreviousPresenter(this, request, gson)

        detailPreviousPresenter.getDetailMatch("lookupevent.php", item)

    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showDetailMatch(data: ArrayList<PreviousItem>) {
        activitydetailprevious_tv_date.text = data.first().dateEvent
        activitydetailprevious_tv_time.text = data.first().strTime
        activitydetailprevious_tv_homename.text = data.first().strHomeTeam
        activitydetailprevious_tv_awayname.text = data.first().strAwayTeam

        if (data.first().intAwayScore != null && data.first().intHomeScore != null) {
            activitydetailprevious_tv_homescore.text = data.first().intHomeScore.toString()
            activitydetailprevious_tv_awayscore.text = data.first().intAwayScore.toString()

            activitydetailprevious_tv_homegoals.text = data.first().strHomeGoalDetails.toString()
            activitydetailprevious_tv_awaygoals.text = data.first().strAwayGoalDetails.toString()
        }

        if (data.first().strAwayYellowCards != null && data.first().strHomeYellowCards !== null
            && data.first().strAwayRedCards != null && data.first().strHomeRedCards != null
        ){
            activitydetailprevious_tv_homeyellowcards.text = data.first().strHomeYellowCards.toString()
            activitydetailprevious_tv_homeredcards.text = data.first().strHomeRedCards.toString()
            activitydetailprevious_tv_awayyellowcards.text = data.first().strAwayYellowCards.toString()
            activitydetailprevious_tv_homeyellowcards.text = data.first().strHomeYellowCards.toString()
        }else{
            activitydetailprevious_tv_homeyellowcards.text = "-"
            activitydetailprevious_tv_homeredcards.text = "-"
            activitydetailprevious_tv_awayyellowcards.text = "-"
            activitydetailprevious_tv_homeyellowcards.text = "-"
        }


        detailPreviousPresenter.getDetailTeamHome(
            "lookupteam.php",
            data.first().idHomeTeam.toString()
        )
        detailPreviousPresenter.getDetailTeamAway(
            "lookupteam.php",
            data.first().idAwayTeam.toString()
        )

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
        }
        return true
    }

    override fun showDetailTeamHome(data: ArrayList<TeamsItem>) {
        Picasso.get().load(data.first().strTeamBadge).into(activitydetailprevious_iv_homethumb);
    }

    override fun showDetailTeamAway(data: ArrayList<TeamsItem>) {
        Picasso.get().load(data.first().strTeamBadge).into(activitydetailprevious_iv_awaythumb);
    }

}
