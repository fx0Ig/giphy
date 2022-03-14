package com.example.giphy.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.giphy.domain.Gif
import retrofit2.http.GET

@Dao
interface GifDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGifs(gifs: List<Gif>)

    @Query("SELECT * FROM gif_table WHERE `query`==:query")
    suspend fun getGifs(query: String): List<Gif>
}