package com.example.kadesubmisidua.view.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.kadesubmisidua.R
import com.example.kadesubmisidua.adapter.PagerMatchAdapter
import com.example.kadesubmisidua.view.fragment.PreviousFragment
import kotlinx.android.synthetic.main.activity_match.*

class MatchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val idLeague = intent.getStringExtra("id_league")

        activitymatch_vp.adapter = PagerMatchAdapter(supportFragmentManager,idLeague)
        activitymatch_tl.setupWithViewPager(activitymatch_vp)

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
        }
        return true
    }


}
