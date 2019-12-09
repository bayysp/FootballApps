package com.example.kadesubmisidua.view.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_favorite.*

import com.example.kadesubmisidua.R
import com.example.kadesubmisidua.adapter.pageradapter.PagerFavoriteAdapter
import kotlinx.android.synthetic.main.activity_match.*
import kotlinx.android.synthetic.main.fragment_favorite.view.*

/**
 * A simple [Fragment] subclass.
 */
class FavoriteFragment : Fragment() {

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
                childFragmentManager
            )

        view.fragmentfavorite_tl.setupWithViewPager(fragmentfavorite_vp)
    }
}
