package com.example.giphy.data

import androidx.room.Database
import androidx.room.DatabaseConfiguration
import androidx.room.InvalidationTracker
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.example.giphy.domain.Gif


@Database(entities = [Gif::class], version = 1)
abstract class GifDatabase : RoomDatabase() {


    abstract fun gifDao(): GifDao


}