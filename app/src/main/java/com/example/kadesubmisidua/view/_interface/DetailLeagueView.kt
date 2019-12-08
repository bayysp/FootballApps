package com.example.kadesubmisidua.view._interface

import com.example.kadesubmisidua.model.league.LeaguesItem

interface DetailLeagueView {
    fun showLoading()
    fun hideLoading()
    fun showDetailLeague(data: ArrayList<LeaguesItem>)
}