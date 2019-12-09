package com.example.kadesubmisidua.adapter.pageradapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.kadesubmisidua.model.favorite.FavoriteMatch
import com.example.kadesubmisidua.view.fragment.childfragment.FavoriteNextFragment
import com.example.kadesubmisidua.view.fragment.childfragment.FavoritePreviousFragment

class PagerFavoriteAdapter(fragmentManager : FragmentManager,
                           favoritePreviousMatch : ArrayList<FavoriteMatch>,
                           favoriteNextMatch : ArrayList<FavoriteMatch>) : FragmentPagerAdapter(fragmentManager) {

    private val pages = listOf(
        FavoritePreviousFragment(
            favoritePreviousMatch
        ),
        FavoriteNextFragment(
            favoriteNextMatch
        )
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