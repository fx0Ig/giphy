package com.example.giphy.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gif_table")
data class Gif(
    @PrimaryKey
    val id: String,
    val imgURL: String,
    val query: String
)
