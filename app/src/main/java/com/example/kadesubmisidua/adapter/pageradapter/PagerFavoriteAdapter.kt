package com.example.kadesubmisidua.adapter.pageradapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.kadesubmisidua.model.favorite.FavoriteMatch
import com.example.kadesubmisidua.model.favorite.FavoriteTeam
import com.example.kadesubmisidua.view.fragment.childfragment.FavoriteNextFragment
import com.example.kadesubmisidua.view.fragment.childfragment.FavoritePreviousFragment
import com.example.kadesubmisidua.view.fragment.childfragment.FavoriteTeamFragment

class PagerFavoriteAdapter(fragmentManager : FragmentManager,
                           favoritePreviousMatch : ArrayList<FavoriteMatch>,
                           favoriteNextMatch : ArrayList<FavoriteMatch>,
                           favoriteTeam : ArrayList<FavoriteTeam>) : FragmentPagerAdapter(fragmentManager) {

    private val pages = listOf(
        FavoritePreviousFragment(
            favoritePreviousMatch
        ),
        FavoriteNextFragment(
            favoriteNextMatch
        ),
        FavoriteTeamFragment(
            favoriteTeam
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
            2 -> "Team"
            else -> " "
        }
    }
}