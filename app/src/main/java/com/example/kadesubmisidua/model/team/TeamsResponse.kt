package com.example.kadesubmisidua.model.team

import com.google.gson.annotations.SerializedName

data class TeamsResponse(

	@field:SerializedName("teams")
	val teams: ArrayList<TeamsItem>
)