package com.example.kadesubmisidua.view._interface

import com.example.kadesubmisidua.model.nextmatch.NextItem
import com.example.kadesubmisidua.model.team.TeamsItem

interface DetailView {
    fun showLoading()
    fun hideLoading()
    fun showDetailMatch(data: ArrayList<NextItem>)

    fun showDetailTeamHome(data : ArrayList<TeamsItem>)
    fun showDetailTeamAway(data : ArrayList<TeamsItem>)
}