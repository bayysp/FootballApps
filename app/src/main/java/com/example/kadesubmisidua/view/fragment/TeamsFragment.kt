package com.example.kadesubmisidua.view.fragment


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.kadesubmisidua.R
import com.example.kadesubmisidua.adapter.recycleradapter.TeamsAdapter
import com.example.kadesubmisidua.api.ApiRepository
import com.example.kadesubmisidua.model.team.TeamsItem
import com.example.kadesubmisidua.util.invisible
import com.example.kadesubmisidua.util.visible
import com.example.kadesubmisidua.view._interface.TeamsView
import com.example.kadesubmisidua.view.activity.DetailTeamActivity
import com.example.kadesubmisidua.view.presenter.TeamsPresenter
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_teams.*


/**
 * A simple [Fragment] subclass.
 */
class TeamsFragment : Fragment(), TeamsView {

    private lateinit var progressBar: ProgressBar

    private var teamsItem : ArrayList<TeamsItem> = arrayListOf()
    private lateinit var teamsPresenter: TeamsPresenter
    private lateinit var teamsAdapter : TeamsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_teams, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressBar = view.findViewById(R.id.fragmentteams_pb)
        val request = ApiRepository()
        val gson = Gson()

        teamsPresenter = TeamsPresenter(this,request,gson)
        teamsAdapter = TeamsAdapter(teamsItem){
            Toast.makeText(context,"Clicked",Toast.LENGTH_SHORT).show()
            val intent = Intent(context,DetailTeamActivity::class.java)
            intent.putExtra("id_team",it.idTeam)
            startActivity(intent)
        }

        fragmentteams_rv.layoutManager = GridLayoutManager(context,3)

        fragmentteams_rv.adapter = teamsAdapter

        teamsPresenter.getTeamsList("lookup_all_teams.php","4328")
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showTeamList(data: ArrayList<TeamsItem>) {
        teamsItem.clear()
        teamsItem.addAll(data)
        teamsAdapter.notifyDataSetChanged()
    }


}
