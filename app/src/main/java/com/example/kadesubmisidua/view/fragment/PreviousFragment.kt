package com.example.kadesubmisidua.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kadesubmisidua.R
import com.example.kadesubmisidua.adapter.PreviousMatchAdapter
import com.example.kadesubmisidua.api.ApiRepository
import com.example.kadesubmisidua.model.PreviousItem
import com.example.kadesubmisidua.util.invisible
import com.example.kadesubmisidua.util.visible
import com.example.kadesubmisidua.view._interface.PreviousMatchView
import com.example.kadesubmisidua.view.presenter.PreviousMatchPresenter
import com.example.kadesubmisidua.R.array.league
import com.example.kadesubmisidua.R.array.id_league
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_previous.*
import org.jetbrains.anko.sdk27.coroutines.onItemSelectedListener

class PreviousFragment : Fragment(), PreviousMatchView {

    private var previousMatchItem: MutableList<PreviousItem> = mutableListOf()

    private lateinit var previousMatchPresenter: PreviousMatchPresenter
    private lateinit var previousMatchAdapter: PreviousMatchAdapter

    private lateinit var progressBar: ProgressBar
    private lateinit var rvPreviousMatch: RecyclerView

    private var idLeagueIndex : Int = 0
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

        progressBar = view.findViewById(R.id.fragmentprevious_pb)

        val request = ApiRepository()
        val gson = Gson()

        previousMatchPresenter = PreviousMatchPresenter(this,request,gson)

        previousMatchAdapter = PreviousMatchAdapter(previousMatchItem){
            Toast.makeText(context,"Clicked", Toast.LENGTH_SHORT).show()
        }

        fragmentprevious_rv_previousmatch.layoutManager = LinearLayoutManager(context)
        fragmentprevious_rv_previousmatch.adapter = previousMatchAdapter

        previousMatchPresenter.getPreviousMatchList("eventspastleague.php","4328")

        val spinnerItems = resources.getStringArray(league)
        val idLeague = resources.getStringArray(id_league)
        val spinnerAdapter = ArrayAdapter(context!!,android.R.layout.simple_spinner_dropdown_item,spinnerItems)
        fragmentprevious_sp.adapter = spinnerAdapter

        fragmentprevious_sp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

                Log.d("PreviousFragment","get league position: "+position)
                Log.d("PreviousFragment","get league id: "+idLeague[position])
                previousMatchPresenter.getPreviousMatchList("eventspastleague.php",idLeague[position].toString())
            }

        }

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
