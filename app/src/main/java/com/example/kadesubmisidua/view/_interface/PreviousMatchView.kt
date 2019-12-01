package com.example.kadesubmisidua.view._interface

import com.example.kadesubmisidua.model.PreviousItem

interface PreviousMatchView {
    fun showLoading()
    fun hideLoading()
    fun showPreviousMatchList(data: ArrayList<PreviousItem>)
}