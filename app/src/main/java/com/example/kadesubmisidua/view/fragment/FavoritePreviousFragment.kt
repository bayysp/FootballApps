package com.example.kadesubmisidua.view.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.kadesubmisidua.R
import com.example.kadesubmisidua.model.previousmatch.PreviousItem

/**
 * A simple [Fragment] subclass.
 */
class FavoritePreviousFragment : Fragment() {

    private var previousMatchItem : MutableList<PreviousItem> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_previous, container, false)
    }


}
