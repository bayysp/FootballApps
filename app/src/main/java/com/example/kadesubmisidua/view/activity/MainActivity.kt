package com.example.kadesubmisidua.view.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kadesubmisidua.R
import com.example.kadesubmisidua.R.array.*
import com.example.kadesubmisidua.adapter.LeagueAdapter
import com.example.kadesubmisidua.model.league.LeagueItem
import com.example.kadesubmisidua.view.fragment.FavoriteFragment
import com.example.kadesubmisidua.view.fragment.LeagueFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {


    private var selectedFragment : Fragment = LeagueFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadFragment(selectedFragment)

        activitymain_bnv.setOnNavigationItemSelectedListener(this)


    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.main_menu_league -> {
                selectedFragment = LeagueFragment()
            }

            R.id.main_menu_favorite -> {
                selectedFragment = FavoriteFragment()
            }
        }

        return loadFragment(selectedFragment)
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

    private fun loadFragment(selectedFragment: Fragment): Boolean {
        if (selectedFragment != null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.activitymain_container, selectedFragment)
                .addToBackStack(null)
                .commit()
            Log.d("MainActivityOptions", "selectedFragment is : " + selectedFragment);


            return true
        }
        return false
    }

}
