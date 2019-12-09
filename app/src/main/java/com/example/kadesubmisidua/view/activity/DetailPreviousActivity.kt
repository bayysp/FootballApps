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
import com.example.kadesubmisidua.model.previousmatch.PreviousItem
import com.example.kadesubmisidua.model.team.TeamsItem
import com.example.kadesubmisidua.view._interface.DetailPreviousView
import com.example.kadesubmisidua.view.presenter.DetailPreviousPresenter
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_previous.*
import org.jetbrains.anko.db.*
import com.example.kadesubmisidua.database.database
import com.example.kadesubmisidua.model.favorite.FavoriteMatch
import com.google.android.material.snackbar.Snackbar
import org.jetbrains.anko.design.snackbar

class DetailPreviousActivity : AppCompatActivity(), DetailPreviousView {

    private lateinit var detailPreviousPresenter: DetailPreviousPresenter

    private var isFavorite = false
    private var menuItem: Menu? = null
    private lateinit var previousItem: PreviousItem
    private lateinit var  idEvent : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_previous)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val item = intent.getStringExtra("id_event")
        idEvent = item
        val request = ApiRepository()
        val gson = Gson()
        detailPreviousPresenter = DetailPreviousPresenter(this, request, gson)

        detailPreviousPresenter.getDetailMatch("lookupevent.php", item)

        setFavoriteState()

    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showDetailMatch(data: ArrayList<PreviousItem>) {
        previousItem = data.first()

        activitydetailprevious_tv_date.text = data.first().dateEvent
        activitydetailprevious_tv_time.text = data.first().strTime
        activitydetailprevious_tv_homename.text = data.first().strHomeTeam
        activitydetailprevious_tv_awayname.text = data.first().strAwayTeam

        if (data.first().intAwayScore != null && data.first().intHomeScore != null) {
            activitydetailprevious_tv_homescore.text = data.first().intHomeScore.toString()
            activitydetailprevious_tv_awayscore.text = data.first().intAwayScore.toString()

            activitydetailprevious_tv_homegoals.text = data.first().strHomeGoalDetails.toString()
            activitydetailprevious_tv_awaygoals.text = data.first().strAwayGoalDetails.toString()
        }

        if (data.first().strAwayYellowCards != null && data.first().strHomeYellowCards !== null
            && data.first().strAwayRedCards != null && data.first().strHomeRedCards != null
        ) {
            activitydetailprevious_tv_homeyellowcards.text =
                data.first().strHomeYellowCards.toString()
            activitydetailprevious_tv_homeredcards.text = data.first().strHomeRedCards.toString()
            activitydetailprevious_tv_awayyellowcards.text =
                data.first().strAwayYellowCards.toString()
            activitydetailprevious_tv_homeyellowcards.text =
                data.first().strHomeYellowCards.toString()
        } else {
            activitydetailprevious_tv_homeyellowcards.text = "-"
            activitydetailprevious_tv_homeredcards.text = "-"
            activitydetailprevious_tv_awayyellowcards.text = "-"
            activitydetailprevious_tv_homeyellowcards.text = "-"
        }


        detailPreviousPresenter.getDetailTeamHome(
            "lookupteam.php",
            data.first().idHomeTeam.toString()
        )
        detailPreviousPresenter.getDetailTeamAway(
            "lookupteam.php",
            data.first().idAwayTeam.toString()
        )

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

    private fun addToFavorite() {
        try {
            database.use {
                insert(
                    FavoriteMatch.TABLE_FAVORITE,
                    FavoriteMatch.EVENT_ID to previousItem.idEvent,
                    FavoriteMatch.EVENT_CATEGORY to "previousitem"
                )
                Toast.makeText(applicationContext,"Added to FavoriteMatch Database",Toast.LENGTH_SHORT).show()
            }
        } catch (ex: Exception) {
            Log.d("DetailPreviousActivity", "Error Adding to FavoriteMatch DB")
            Toast.makeText(applicationContext,"Failed Added to FavoriteMatch Database",Toast.LENGTH_SHORT).show()
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
            Toast.makeText(applicationContext,"Remove from FavoriteMatch Database",Toast.LENGTH_SHORT).show()
        }catch (ex : Exception){
            Log.d("DetailPreviousActivity", "Error Remove from FavoriteMatch DB")
            Toast.makeText(applicationContext,"Failed to Remove From FavoriteMatch Database",Toast.LENGTH_SHORT).show()
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

    override fun showDetailTeamHome(data: ArrayList<TeamsItem>) {
        Picasso.get().load(data.first().strTeamBadge).into(activitydetailprevious_iv_homethumb);
    }

    override fun showDetailTeamAway(data: ArrayList<TeamsItem>) {
        Picasso.get().load(data.first().strTeamBadge).into(activitydetailprevious_iv_awaythumb);
    }

}
