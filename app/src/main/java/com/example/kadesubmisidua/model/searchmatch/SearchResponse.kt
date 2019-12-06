package com.example.kadesubmisidua.model.searchmatch

import com.google.gson.annotations.SerializedName


data class SearchResponse(

	@field:SerializedName("event")
	val event: ArrayList<SearchItem>
)