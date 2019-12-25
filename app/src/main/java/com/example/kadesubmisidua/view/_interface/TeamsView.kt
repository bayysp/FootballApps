package com.example.kadesubmisidua.view._interface

import com.example.kadesubmisidua.model.team.TeamsItem

interface TeamsView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data : ArrayList<TeamsItem>)
}