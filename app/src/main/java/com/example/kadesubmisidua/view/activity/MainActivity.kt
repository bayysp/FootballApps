package com.example.kadesubmisidua.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.kadesubmisidua.R
import com.example.kadesubmisidua.view.fragment.NextFragment
import com.example.kadesubmisidua.view.fragment.PreviousFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private var selectedFragment : Fragment = NextFragment()

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

}
