package com.example.kadesubmisidua.view.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kadesubmisidua.R
import com.example.kadesubmisidua.adapter.recycleradapter.LeagueAdapter
import com.example.kadesubmisidua.model.league.LeagueItem
import kotlinx.android.synthetic.main.fragment_league.*

/**
 * A simple [Fragment] subclass.
 */
class LeagueFragment : Fragment() {

    private lateinit var leagueAdapter: LeagueAdapter
    private var leagueItems: ArrayList<LeagueItem> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_league, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()

        fragmentleague_rv.layoutManager = LinearLayoutManager(context)
        fragmentleague_rv.adapter =
            LeagueAdapter(context, leagueItems)
    }

    private fun initData() {
        val leagueTitle = resources.getStringArray(R.array.league)
        val leagueThumb = resources.obtainTypedArray(R.array.club_image)
        val leagueId = resources.getStringArray(R.array.id_league)

        leagueItems.clear()

        for (i in leagueTitle.indices) {
            leagueItems.add(
                LeagueItem(
                    leagueTitle[i],
                    leagueThumb.getResourceId(i, 0),
                    leagueId[i]
                )
            )
        }

        leagueThumb.recycle()
    }

}
