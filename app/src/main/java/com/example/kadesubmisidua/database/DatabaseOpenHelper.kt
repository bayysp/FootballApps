package com.example.kadesubmisidua.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.kadesubmisidua.model.favorite.FavoriteMatch
import org.jetbrains.anko.db.*

class DatabaseOpenHelper(context: Context) :
    ManagedSQLiteOpenHelper(
        context,
        "FavoriteMatch.db",
        null,
        1
    ) {

    companion object {
        private var instance: DatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(context: Context): DatabaseOpenHelper {
            if (instance == null) {
                instance = DatabaseOpenHelper(context.applicationContext)
            }
            return instance!!
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        //create youre table
        db?.createTable(
            FavoriteMatch.TABLE_FAVORITE,
            true,
            FavoriteMatch.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            FavoriteMatch.EVENT_ID to TEXT + UNIQUE,
            FavoriteMatch.EVENT_CATEGORY to TEXT

        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(FavoriteMatch.TABLE_FAVORITE, true)
    }

}

val Context.database: DatabaseOpenHelper
    get() = DatabaseOpenHelper.getInstance(applicationContext)