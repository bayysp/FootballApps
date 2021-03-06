package com.example.kadesubmisidua.view._interface

import com.example.kadesubmisidua.model.nextmatch.NextItem

interface NextMatchView {
    fun showLoading()
    fun hideLoading()
    fun showNextMatchList(data: ArrayList<NextItem>)
}