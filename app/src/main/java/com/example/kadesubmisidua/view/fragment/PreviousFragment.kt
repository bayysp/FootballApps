package com.example.kadesubmisidua.view.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView

import com.example.kadesubmisidua.R
import com.example.kadesubmisidua.adapter.PreviousMatchAdapter
import com.example.kadesubmisidua.model.PreviousItem
import com.example.kadesubmisidua.view.presenter.PreviousMatchPresenter

/**
 * A simple [Fragment] subclass.
 */
class PreviousFragment : Fragment() {

    private var previousMatchItem : MutableList<PreviousItem> = mutableListOf()

    private lateinit var previousMatchPresenter: PreviousMatchPresenter
    private lateinit var previousMatchAdapter: PreviousMatchAdapter

    private lateinit var progressBar : ProgressBar
    private lateinit var rvPreviousMatch : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_previous, container, false)
    }



}
