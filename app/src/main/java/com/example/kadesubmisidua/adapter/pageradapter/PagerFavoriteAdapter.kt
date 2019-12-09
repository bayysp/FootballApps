package com.example.kadesubmisidua.adapter.pageradapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.kadesubmisidua.view.fragment.FavoriteNextFragment
import com.example.kadesubmisidua.view.fragment.FavoritePreviousFragment

class PagerFavoriteAdapter(fragmentManager : FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    private val pages = listOf(
        FavoritePreviousFragment(),
        FavoriteNextFragment()
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
            else -> " "
        }
    }
}