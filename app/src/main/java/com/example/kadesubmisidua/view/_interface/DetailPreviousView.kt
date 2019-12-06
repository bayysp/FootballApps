package com.example.kadesubmisidua.view._interface

import com.example.kadesubmisidua.model.nextmatch.NextItem
import com.example.kadesubmisidua.model.previousmatch.PreviousItem
import com.example.kadesubmisidua.model.team.TeamsItem

interface DetailPreviousView {
    fun showLoading()
    fun hideLoading()
    fun showDetailMatch(data: ArrayList<PreviousItem>)

    fun showDetailTeamHome(data : ArrayList<TeamsItem>)
    fun showDetailTeamAway(data : ArrayList<TeamsItem>)
}