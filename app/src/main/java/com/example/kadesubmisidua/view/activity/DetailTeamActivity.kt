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
import com.example.kadesubmisidua.model.favorite.FavoriteTeam
import com.example.kadesubmisidua.model.team.TeamsItem
import com.example.kadesubmisidua.util.invisible
import com.example.kadesubmisidua.util.visible
import com.example.kadesubmisidua.view._interface.DetailTeamView
import com.example.kadesubmisidua.view.presenter.DetailTeamPresenter
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_team.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class DetailTeamActivity : AppCompatActivity() , DetailTeamView {

    private var isFavorite = false
    private var menuItem: Menu? = null

    private lateinit var detailTeamPresenter : DetailTeamPresenter
    private lateinit var teamItem : TeamsItem

    private lateinit var idTeam : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val request = ApiRepository()
        val gson = Gson()

        val idTeam = intent.getStringExtra("id_team")
        this.idTeam = idTeam

        detailTeamPresenter = DetailTeamPresenter(this,request,gson)
        detailTeamPresenter.getDetailTeam("lookupteam.php",idTeam)

        setFavoriteState()
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
                    FavoriteTeam.TABLE_TEAM,
                    FavoriteTeam.TEAM_ID to teamItem.idTeam,
                    FavoriteTeam.TEAM_NAME to teamItem.strTeam,
                    FavoriteTeam.TEAM_THUMB to teamItem.strTeamBadge
                )
                Toast.makeText(applicationContext,"Added to FavoriteTeam Database", Toast.LENGTH_SHORT).show()
            }
        } catch (ex: Exception) {
            Log.d("DetailTeamActivity", "Error Adding to FavoriteTeam DB")
            Toast.makeText(applicationContext,"Failed Added to FavoriteTeam Database", Toast.LENGTH_SHORT).show()
        }
    }

    private fun deleteFromFavorite() {
        try {
            database.use {
                delete(
                    FavoriteTeam.TABLE_TEAM,
                    "TEAM_ID = {idTeam}",
                    "idTeam" to idTeam
                )
            }
            Toast.makeText(applicationContext,"Remove from FavoriteMatch Database", Toast.LENGTH_SHORT).show()
        }catch (ex : Exception){
            Log.d("DetailTeamActivity", "Error Remove from FavoriteMatch DB")
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
                FavoriteTeam.TABLE_TEAM
            ).whereArgs("(TEAM_ID = {idTeam})", "idTeam" to idTeam)

            val favorite = result.parseList(classParser<FavoriteTeam>())

            if (favorite.isNotEmpty()) isFavorite = true
        }
    }

    override fun showLoading() {
        activitydetailteam_pb.visible()
    }

    override fun hideLoading() {
        activitydetailteam_pb.invisible()
    }

    override fun showDetailTeam(data: ArrayList<TeamsItem>) {
        teamItem = data.first()

        activitydetailteam_tv_teamtitle.text = data.first().strTeam
        activitydetailteam_tv_desc.text = data.first().strDescriptionEN

        if (data.first().strTeamBadge != null){
            Picasso.get().load(data.first().strTeamBadge.toString()).into(activitydetailteam_iv_team)
        }


    }
}
