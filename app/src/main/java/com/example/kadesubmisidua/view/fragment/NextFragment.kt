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
import com.example.kadesubmisidua.adapter.recycleradapter.NextMatchAdapter
import com.example.kadesubmisidua.api.ApiRepository
import com.example.kadesubmisidua.model.nextmatch.NextItem
import com.example.kadesubmisidua.util.invisible
import com.example.kadesubmisidua.util.visible
import com.example.kadesubmisidua.view._interface.NextMatchView
import com.example.kadesubmisidua.view.activity.DetailActivity
import com.example.kadesubmisidua.view.presenter.NextMatchPresenter
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_next.*

/**
 * A simple [Fragment] subclass.
 */
class NextFragment(private val idLeague : String) : Fragment() , NextMatchView {

    private var nextMatchItem : MutableList<NextItem> = mutableListOf()
    private lateinit var nextMatchPresenter : NextMatchPresenter
    private lateinit var nextMatchAdapter : NextMatchAdapter

    private lateinit var progressBar : ProgressBar


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_next, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressBar = view.findViewById(R.id.fragmentnext_pb)

        val request = ApiRepository()
        val gson = Gson()
        nextMatchPresenter = NextMatchPresenter(this,request,gson)

        nextMatchAdapter =
            NextMatchAdapter(nextMatchItem) {
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra("id_event", it.idEvent)
                startActivity(intent)
            }

        fragmentnext_rv_nextmatch.layoutManager = LinearLayoutManager(context)
        fragmentnext_rv_nextmatch.adapter = nextMatchAdapter

        nextMatchPresenter.getNextMatchList("eventsnextleague.php",idLeague)

    }



    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showNextMatchList(data: ArrayList<NextItem>) {
        nextMatchItem.clear()
        nextMatchItem.addAll(data)
        nextMatchAdapter.notifyDataSetChanged()
    }
}
