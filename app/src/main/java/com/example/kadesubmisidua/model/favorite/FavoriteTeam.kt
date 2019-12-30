package com.example.kadesubmisidua.model.favorite

data class FavoriteTeam(
    val id : Long?,
    val teamId : String?,
    val teamName : String?,
    val teamThumb : String?
) {
    companion object{
        const val TABLE_TEAM = "TABLE_TEAM"
        const val ID : String = "ID_"
        const val TEAM_ID = "TEAM_ID"
        const val TEAM_NAME = "TEAM_NAME"
        const val TEAM_THUMB = "TEAM_THUMB"
    }
}