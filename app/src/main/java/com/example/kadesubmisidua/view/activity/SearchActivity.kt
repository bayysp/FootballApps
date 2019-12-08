package com.example.kadesubmisidua.view.activity

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kadesubmisidua.R
import com.example.kadesubmisidua.adapter.SearchAdapter
import com.example.kadesubmisidua.api.ApiRepository
import com.example.kadesubmisidua.model.searchmatch.SearchItem
import com.example.kadesubmisidua.util.invisible
import com.example.kadesubmisidua.util.visible
import com.example.kadesubmisidua.view.presenter.SearchPresenter
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity(), com.example.kadesubmisidua.view._interface.SearchView {

    private var searchItem: MutableList<SearchItem> = mutableListOf()

    private lateinit var searchPresenter: SearchPresenter
    private lateinit var searchAdapter: SearchAdapter

    private lateinit var progressBar: ProgressBar
    private lateinit var rvSearchMatch : RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val request = ApiRepository()
        val gson = Gson()
        searchPresenter = SearchPresenter(this,request,gson)

        searchAdapter = SearchAdapter(applicationContext,searchItem){
            Toast.makeText(applicationContext,"Clicked", Toast.LENGTH_SHORT).show()
        }

        searchactivity_rv.layoutManager = LinearLayoutManager(applicationContext)
        searchactivity_rv.adapter = searchAdapter

        //detail search item is liverpool ehehehe
        searchPresenter.getSearchMatchList("searchevents.php","liverpool")
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.search_option_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView =
            menu?.findItem(R.id.search_option_search)?.actionView as androidx.appcompat.widget.SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))

        searchView.queryHint = "Cari Pertandingan"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.d("MainActivity", "onQuerySubmit val : " + query);
                searchPresenter.getSearchMatchList("searchevents.php",query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Log.d("MainActivity", "onQueryChange val : " + newText);
                searchPresenter.getSearchMatchList("searchevents.php",newText)
                return true
            }

        })

        return true
    }

    override fun showLoading() {
        searchactivity_pb.visible()
    }

    override fun hideLoading() {
        searchactivity_pb.invisible()
    }

    override fun showSearchMatch(data: ArrayList<SearchItem>) {
        searchItem.clear()
        searchItem.addAll(data)
        searchAdapter.notifyDataSetChanged()
    }
}
