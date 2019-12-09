package com.example.kadesubmisidua.view.activity

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.kadesubmisidua.R
import com.example.kadesubmisidua.api.ApiRepository
import com.example.kadesubmisidua.database.database
import com.example.kadesubmisidua.model.favorite.FavoriteMatch
import com.example.kadesubmisidua.model.nextmatch.NextItem
import com.example.kadesubmisidua.model.team.TeamsItem
import com.example.kadesubmisidua.view._interface.DetailView
import com.example.kadesubmisidua.view.presenter.DetailPresenter
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class DetailActivity : AppCompatActivity(), DetailView {

    private lateinit var detailPresenter: DetailPresenter

    private var isFavorite = false
    private var menuItem: Menu? = null
    private lateinit var nextItem: NextItem
    private lateinit var  idEvent : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val item = intent.getStringExtra("id_event")
        idEvent = item

        val request = ApiRepository()
        val gson = Gson()
        detailPresenter = DetailPresenter(this, request, gson)

        detailPresenter.getDetailMatch("lookupevent.php", item)

        setFavoriteState()

    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showDetailMatch(data: ArrayList<NextItem>) {
        nextItem = data.first()

        activitydetail_tv_date.text = data.first().dateEvent
        activitydetail_tv_time.text = data.first().strTime
        activitydetail_tv_homename.text = data.first().strHomeTeam
        activitydetail_tv_awayname.text = data.first().strAwayTeam

        detailPresenter.getDetailTeamHome("lookupteam.php", data.first().idHomeTeam.toString())
        detailPresenter.getDetailTeamAway("lookupteam.php", data.first().idAwayTeam.toString())
    }

    override fun showDetailTeamHome(data: ArrayList<TeamsItem>) {

        Picasso.get().load(data.first().strTeamBadge).into(activitydetail_iv_homethumb)
    }

    override fun showDetailTeamAway(data: ArrayList<TeamsItem>) {
        Picasso.get().load(data.first().strTeamBadge).into(activitydetail_iv_awaythumb)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
            R.id.detail_option_favoritematch -> {
                if (isFavorite) {
                    deleteFromFavorite()
                    isFavorite = false
                    setFavoriteIcon()
                } else {
                    addToFavorite()
                    isFavorite = true
                    setFavoriteIcon()
                }
            }
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_option_favorite, menu)

        menuItem = menu

        if (isFavorite) {
            menu?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.favorite)
        } else {
            menu?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.not_favorite)
        }
        return super.onCreateOptionsMenu(menu)
    }

    private fun addToFavorite() {
        try {
            database.use {
                insert(
                    FavoriteMatch.TABLE_FAVORITE,
                    FavoriteMatch.EVENT_ID to nextItem.idEvent,
                    FavoriteMatch.EVENT_CATEGORY to "nextitem",
                    FavoriteMatch.EVENT_HOMETEAM to nextItem.strHomeTeam,
                    FavoriteMatch.EVENT_AWAYTEAM to nextItem.strAwayTeam
                )
                Toast.makeText(applicationContext,"Added to FavoriteMatch Database", Toast.LENGTH_SHORT).show()
            }
        } catch (ex: Exception) {
            Log.d("DetailPreviousActivity", "Error Adding to FavoriteMatch DB")
            Log.d("DetailPreviousActivity", ex.message)
            Toast.makeText(applicationContext,"Failed Added to FavoriteMatch Database", Toast.LENGTH_SHORT).show()
        }
    }

    private fun deleteFromFavorite() {
        try {
            database.use {
                delete(
                    FavoriteMatch.TABLE_FAVORITE,
                    "EVENT_ID = {idEvent}",
                    "idEvent" to idEvent
                )
            }
            Toast.makeText(applicationContext,"Remove from FavoriteMatch Database", Toast.LENGTH_SHORT).show()
        }catch (ex : Exception){
            Log.d("DetailPreviousActivity", "Error Remove from FavoriteMatch DB")
            Toast.makeText(applicationContext,"Failed to Remove From FavoriteMatch Database", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setFavoriteIcon() {

        if (isFavorite) {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.favorite)
        } else {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.not_favorite)
        }
    }

    private fun setFavoriteState() {
        database.use {
            val result = select(
                FavoriteMatch.TABLE_FAVORITE
            ).whereArgs("(EVENT_ID = {idEvent})", "idEvent" to idEvent)

            val favorite = result.parseList(classParser<FavoriteMatch>())

            if (favorite.isNotEmpty()) isFavorite = true
        }
    }

}
