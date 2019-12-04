package com.example.kadesubmisidua.view._interface

import com.example.kadesubmisidua.model.NextItem

interface DetailView {
    fun showLoading()
    fun hideLoading()
    fun showDetailMatch(data: ArrayList<NextItem>)
}