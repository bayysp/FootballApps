package com.example.kadesubmisidua.view.fragment


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kadesubmisidua.R
import com.example.kadesubmisidua.adapter.recycleradapter.ClassementAdapter
import com.example.kadesubmisidua.api.ApiRepository
import com.example.kadesubmisidua.model.classement.ClassementItem
import com.example.kadesubmisidua.model.team.TeamsItem
import com.example.kadesubmisidua.util.invisible
import com.example.kadesubmisidua.util.visible
import com.example.kadesubmisidua.view._interface.ClassementView
import com.example.kadesubmisidua.view.presenter.ClassementPresenter
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_classement.*

/**
 * A simple [Fragment] subclass.
 */
class ClassementFragment(private val idLeague: String) : Fragment(), ClassementView {


    private var classementItem: ArrayList<ClassementItem> = arrayListOf()

    private var idTeam: ArrayList<String> = arrayListOf()
    private var teamThumb : ArrayList<TeamsItem> = arrayListOf()

    private lateinit var classementPresenter: ClassementPresenter
    private lateinit var classementAdapter: ClassementAdapter

    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_classement, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressBar = view.findViewById(R.id.fragmentclassement_pb)

        val request = ApiRepository()
        val gson = Gson()

        classementPresenter = ClassementPresenter(this, request, gson)

        Log.d("ClassementFragment", "idleague : " + idLeague)

        //https://www.thesportsdb.com/api/v1/json/1/lookuptable.php?l=4387
        classementPresenter.getClassementList("lookuptable.php", idLeague)

        classementAdapter = ClassementAdapter(classementItem,idTeam)


        fragmentclassement_rv.layoutManager = LinearLayoutManager(context)
        fragmentclassement_rv.adapter = classementAdapter
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showClassementList(data: ArrayList<ClassementItem>) {

        teamThumb.clear()
        classementItem.clear()


        var idx = 0

        for (i in data) {
            Log.d("ClassementFragment", "added teamid index +"+ idx + ": "+i.teamid.toString())
            idTeam.add(i.teamid.toString())
            idx++
        }

        Log.d("ClassementFragment" , "all Team id : "+idTeam)

        classementItem.addAll(data)
        classementAdapter.notifyDataSetChanged()
    }

//    override fun showTeamThumb(data: ArrayList<TeamsItem>) {
//        teamThumb.addAll(data)
//    }
}
