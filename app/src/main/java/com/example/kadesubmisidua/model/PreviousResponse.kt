package com.example.kadesubmisidua.model

import com.google.gson.annotations.SerializedName

data class PreviousResponse(

	@field:SerializedName("events")
	val events: ArrayList<PreviousItem>
)