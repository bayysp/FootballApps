package com.example.kadesubmisidua.view.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kadesubmisidua.R
import com.example.kadesubmisidua.adapter.pageradapter.PagerFavoriteAdapter
import com.example.kadesubmisidua.database.database
import com.example.kadesubmisidua.model.favorite.FavoriteMatch
import kotlinx.android.synthetic.main.fragment_favorite.*
import kotlinx.android.synthetic.main.fragment_favorite.view.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

/**
 * A simple [Fragment] subclass.
 */
class FavoriteFragment : Fragment() {

    private var favoritePreviousMatch : ArrayList<FavoriteMatch> = arrayListOf()
    private var favoriteNextMatch : ArrayList<FavoriteMatch> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.fragmentfavorite_vp.adapter =
            PagerFavoriteAdapter(
                childFragmentManager,
                showPreviousFavorite(),
                showNextFavorite()
            )

        view.fragmentfavorite_tl.setupWithViewPager(fragmentfavorite_vp)
    }

    override fun onResume() {
        super.onResume()
        fragmentfavorite_vp.adapter =
            PagerFavoriteAdapter(
                childFragmentManager,
                showPreviousFavorite(),
                showNextFavorite()
            )

        fragmentfavorite_tl.setupWithViewPager(fragmentfavorite_vp)
    }

    //this function to get previous favorite
    private fun showPreviousFavorite() : ArrayList<FavoriteMatch> {
        favoritePreviousMatch.clear()
        context?.database?.use {
            val result = select(FavoriteMatch.TABLE_FAVORITE).whereArgs("(EVENT_CATEGORY = {eventCategory})", "eventCategory" to "previousitem")
            val favorite = result.parseList(classParser<FavoriteMatch>())
            favoritePreviousMatch.addAll(favorite)

        }

        return favoritePreviousMatch
    }

    //this function to get next favorite
    private fun showNextFavorite() : ArrayList<FavoriteMatch> {
        favoriteNextMatch.clear()
        context?.database?.use {
            val result = select(FavoriteMatch.TABLE_FAVORITE).whereArgs("(EVENT_CATEGORY = {eventCategory})", "eventCategory" to "nextitem")
            val favorite = result.parseList(classParser<FavoriteMatch>())
            favoriteNextMatch.addAll(favorite)

        }

        return favoriteNextMatch
    }
}
