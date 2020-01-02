package com.example.kadesubmisidua.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kadesubmisidua.R
import com.example.kadesubmisidua.adapter.recycleradapter.TeamsAdapter
import com.example.kadesubmisidua.api.ApiRepository
import com.example.kadesubmisidua.model.team.TeamsItem
import com.example.kadesubmisidua.util.invisible
import com.example.kadesubmisidua.util.visible
import com.example.kadesubmisidua.view._interface.TeamsView
import com.example.kadesubmisidua.view.presenter.TeamsPresenter
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_teams.*
import kotlinx.android.synthetic.main.fragment_teams.*

class TeamsActivity : AppCompatActivity(), TeamsView {

    private lateinit var progressBar: ProgressBar

    private var teamsItem : ArrayList<TeamsItem> = arrayListOf()
    private lateinit var teamsPresenter: TeamsPresenter
    private lateinit var teamsAdapter : TeamsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teams)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val idLeague = intent.getStringExtra("id_league")

        progressBar = findViewById(R.id.activityteams_pb)

        val request = ApiRepository()
        val gson = Gson()

        teamsPresenter = TeamsPresenter(this,request,gson)
        teamsAdapter = TeamsAdapter(teamsItem){
            Toast.makeText(applicationContext,"Clicked", Toast.LENGTH_SHORT).show()
            val intent = Intent(applicationContext,DetailTeamActivity::class.java)
            intent.putExtra("id_team",it.idTeam)
            startActivity(intent)
        }

        activityteams_rv.layoutManager = GridLayoutManager(applicationContext,3) as RecyclerView.LayoutManager?

        activityteams_rv.adapter = teamsAdapter

        teamsPresenter.getTeamsList("lookup_all_teams.php",idLeague)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
        }
        return true
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showTeamList(data: ArrayList<TeamsItem>) {
        teamsItem.clear()
        teamsItem.addAll(data)
        teamsAdapter.notifyDataSetChanged()
    }

}
