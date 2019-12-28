package com.example.kadesubmisidua.view._interface

import com.example.kadesubmisidua.model.team.TeamsItem

interface DetailTeamView {
    fun showLoading()
    fun hideLoading()

    fun showDetailTeam(data : ArrayList<TeamsItem>)
}