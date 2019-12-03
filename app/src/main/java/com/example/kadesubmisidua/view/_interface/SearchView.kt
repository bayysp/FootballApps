package com.example.kadesubmisidua.view._interface

import com.example.kadesubmisidua.model.SearchItem

interface SearchView {
    fun showLoading()
    fun hideLoading()
    fun showSearchMatch(data: ArrayList<SearchItem>)
}