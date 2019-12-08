package com.example.kadesubmisidua.model.league

import com.google.gson.annotations.SerializedName

data class LeagueResponse(

	@field:SerializedName("leagues")
	val leagues: ArrayList<LeaguesItem>
)