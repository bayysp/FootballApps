package com.example.kadesubmisidua.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.kadesubmisidua.R
import com.example.kadesubmisidua.api.ApiRepository
import com.example.kadesubmisidua.model.team.TeamsItem
import com.example.kadesubmisidua.util.invisible
import com.example.kadesubmisidua.util.visible
import com.example.kadesubmisidua.view._interface.DetailTeamView
import com.example.kadesubmisidua.view.presenter.DetailTeamPresenter
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_team.*
import kotlinx.android.synthetic.main.item_classement.*

class DetailTeamActivity : AppCompatActivity() , DetailTeamView {

    private lateinit var detailTeamPresenter : DetailTeamPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val request = ApiRepository()
        val gson = Gson()

        val idTeam = intent.getStringExtra("id_team")

        detailTeamPresenter = DetailTeamPresenter(this,request,gson)
        detailTeamPresenter.getDetailTeam("lookupteam.php",idTeam)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
        }
        return true
    }

    override fun showLoading() {
        activitydetailteam_pb.visible()
    }

    override fun hideLoading() {
        activitydetailteam_pb.invisible()
    }

    override fun showDetailTeam(data: ArrayList<TeamsItem>) {
        activitydetailteam_tv_teamtitle.text = data.first().strTeam
        activitydetailteam_tv_desc.text = data.first().strDescriptionEN
        Picasso.get().load(data.first().strTeamBadge.toString()).into(activitydetailteam_iv_team)

    }
}
