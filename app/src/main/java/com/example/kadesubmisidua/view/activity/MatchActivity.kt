package com.example.kadesubmisidua.view.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.kadesubmisidua.R
import com.example.kadesubmisidua.adapter.pageradapter.PagerMatchAdapter
import com.example.kadesubmisidua.view.fragment.ClassementFragment
import com.example.kadesubmisidua.view.fragment.NextFragment
import com.example.kadesubmisidua.view.fragment.PreviousFragment
import kotlinx.android.synthetic.main.activity_match.*

class MatchActivity : AppCompatActivity() {

    private lateinit var pagerMatchAdapter: PagerMatchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val idLeague = intent.getStringExtra("id_league")

        pagerMatchAdapter = PagerMatchAdapter(supportFragmentManager,idLeague)

        pagerMatchAdapter.addFragment(PreviousFragment(idLeague))
        pagerMatchAdapter.addFragment(NextFragment(idLeague))
        pagerMatchAdapter.addFragment(ClassementFragment(idLeague))

        activitymatch_vp.adapter = pagerMatchAdapter
        activitymatch_tl.setupWithViewPager(activitymatch_vp)

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
        }
        return true
    }


}
