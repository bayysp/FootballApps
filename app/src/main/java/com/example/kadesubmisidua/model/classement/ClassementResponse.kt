package com.example.kadesubmisidua.model.classement

import com.google.gson.annotations.SerializedName


data class ClassementResponse(

    @field:SerializedName("table")
    val table: ArrayList<ClassementItem>
)