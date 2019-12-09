package com.example.kadesubmisidua.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kadesubmisidua.R
import com.example.kadesubmisidua.adapter.recycleradapter.PreviousMatchAdapter
import com.example.kadesubmisidua.api.ApiRepository
import com.example.kadesubmisidua.model.previousmatch.PreviousItem
import com.example.kadesubmisidua.util.invisible
import com.example.kadesubmisidua.util.visible
import com.example.kadesubmisidua.view._interface.PreviousMatchView
import com.example.kadesubmisidua.view.activity.DetailPreviousActivity
import com.example.kadesubmisidua.view.presenter.PreviousMatchPresenter
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_previous.*

class PreviousFragment(private val idLeague : String) : Fragment(), PreviousMatchView {


    private var previousMatchItem: MutableList<PreviousItem> = mutableListOf()

    private lateinit var previousMatchPresenter: PreviousMatchPresenter
    private lateinit var previousMatchAdapter: PreviousMatchAdapter

    private lateinit var progressBar: ProgressBar
//    private lateinit var idLeague : String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_previous, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println(idLeague)
        progressBar = view.findViewById(R.id.fragmentprevious_pb)

        val request = ApiRepository()
        val gson = Gson()




        previousMatchPresenter = PreviousMatchPresenter(this,request,gson)

        previousMatchAdapter =
            PreviousMatchAdapter(
                previousMatchItem
            ) {
                val intent = Intent(context, DetailPreviousActivity::class.java)
                intent.putExtra("id_event", it.idEvent)
                startActivity(intent)
            }

        fragmentprevious_rv_previousmatch.layoutManager = LinearLayoutManager(context)
        fragmentprevious_rv_previousmatch.adapter = previousMatchAdapter

        previousMatchPresenter.getPreviousMatchList("eventspastleague.php",idLeague)


    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showPreviousMatchList(data: ArrayList<PreviousItem>) {
        previousMatchItem.clear()
        previousMatchItem.addAll(data)
        previousMatchAdapter.notifyDataSetChanged()
    }
}
