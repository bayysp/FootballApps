package com.example.kadesubmisidua.model.favorite

data class FavoriteMatch(
    val id : Long?,
    val eventId : String?,
    val eventCategory : String?,
    val eventHomeTeam : String?,
    val eventAwayTeam : String?
) {
    companion object{
        const val TABLE_FAVORITE = "TABLE_FAVORITE"
        const val ID : String = "ID_"
        const val EVENT_ID : String = "EVENT_ID"
        const val EVENT_CATEGORY : String = "EVENT_CATEGORY"
        const val EVENT_HOMETEAM : String = "EVENT_HOMETEAM"
        const val EVENT_AWAYTEAM : String = "EVENT_AWAYTEAM"
    }
}