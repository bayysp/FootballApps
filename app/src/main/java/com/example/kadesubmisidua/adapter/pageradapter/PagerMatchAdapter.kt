package com.example.kadesubmisidua.adapter.pageradapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.kadesubmisidua.view.fragment.ClassementFragment
import com.example.kadesubmisidua.view.fragment.NextFragment
import com.example.kadesubmisidua.view.fragment.PreviousFragment

class PagerMatchAdapter(fragmentManager : FragmentManager,idLeague : String) : FragmentPagerAdapter(fragmentManager){



    private val pages = listOf(
        PreviousFragment(idLeague),
        NextFragment(idLeague),
        ClassementFragment()
    )

    override fun getItem(position: Int): Fragment {
        return pages[position]
    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Previous Match"
            1 -> "Next Match"
            2 -> "Classement"
            else -> " "
        }
    }
}