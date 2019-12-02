package com.example.kadesubmisidua.view.activity

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.example.kadesubmisidua.R
import com.example.kadesubmisidua.view.fragment.NextFragment
import com.example.kadesubmisidua.view.fragment.PreviousFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private var selectedFragment : Fragment = PreviousFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadFragment(selectedFragment)

        main_bottomnav.setOnNavigationItemSelectedListener(this)

    }

    private fun loadFragment(selectedFragment : Fragment) : Boolean {
        if (selectedFragment != null){
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_fragment_container,selectedFragment)
                .addToBackStack(null)
                .commit()
            Log.d("MainActivity", "selectedFragment is : " + selectedFragment);

            return true
        }

        return false
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.main_menu_next -> {
                selectedFragment = NextFragment()
            }

            R.id.main_menu_before -> {
                selectedFragment = PreviousFragment()
            }
        }

        return loadFragment(selectedFragment)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_option_menu,menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu?.findItem(R.id.main_option_search)?.actionView as androidx.appcompat.widget.SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))

        searchView.queryHint = "Cari Pertandingan"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.d("MainActivity", "onQuerySubmit val : " + query);

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Log.d("MainActivity", "onQueryChange val : " + newText);

                return true
            }

        })

        return true
    }
}
