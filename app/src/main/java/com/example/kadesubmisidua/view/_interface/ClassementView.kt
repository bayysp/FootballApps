package com.example.kadesubmisidua.view._interface

import com.example.kadesubmisidua.model.classement.ClassementItem
import com.example.kadesubmisidua.model.team.TeamsItem

interface ClassementView {
    fun showLoading()
    fun hideLoading()
    fun showClassementList(data : ArrayList<ClassementItem>)
//    fun showTeamThumb(data : ArrayList<TeamsItem>)
}