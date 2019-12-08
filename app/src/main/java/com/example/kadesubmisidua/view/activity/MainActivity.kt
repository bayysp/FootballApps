package com.example.kadesubmisidua.view.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kadesubmisidua.R
import com.example.kadesubmisidua.R.array.*
import com.example.kadesubmisidua.adapter.LeagueAdapter
import com.example.kadesubmisidua.model.league.LeagueItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var leagueAdapter : LeagueAdapter
    private var leagueItems : ArrayList<LeagueItem> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initData()

        activitymain_rv.layoutManager = LinearLayoutManager(this)
        activitymain_rv.adapter = LeagueAdapter(applicationContext,leagueItems)

    }

    private fun initData() {
        val leagueTitle = resources.getStringArray(league)
        val leagueThumb = resources.obtainTypedArray(club_image)
        val leagueId = resources.getStringArray(id_league)

        leagueItems.clear()

        for (i in leagueTitle.indices){
            leagueItems.add(
                LeagueItem(
                    leagueTitle[i],
                    leagueThumb.getResourceId(i,0),
                    leagueId[i]
                )
            )
        }

        leagueThumb.recycle()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.main_option_search) {
            Log.d("MainActivity", "move into searchactivity");
            startActivity(Intent(applicationContext, SearchActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_option_menu, menu)

        return true
    }
}
