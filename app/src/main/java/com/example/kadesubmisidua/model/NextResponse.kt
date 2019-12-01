package com.example.kadesubmisidua.model

import com.google.gson.annotations.SerializedName

data class NextResponse(

	@field:SerializedName("events")
	val events: ArrayList<NextItem>
)