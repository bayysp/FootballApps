package com.example.kadesubmisidua.view._interface

import com.example.kadesubmisidua.model.classement.ClassementItem

interface ClassementView {
    fun showLoading()
    fun hideLoading()
    fun showClassementList(data : ArrayList<ClassementItem>)
}