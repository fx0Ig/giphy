package com.example.giphy.overview

import android.app.Application
import android.app.DownloadManager
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.giphy.domain.Gif
import com.example.giphy.network.ApiResponse
import androidx.lifecycle.viewModelScope
import com.example.giphy.data.GifDao
import com.example.giphy.network.GiphyApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.standalone.KoinComponent
import org.koin.standalone.StandAloneContext
import java.util.concurrent.locks.Lock


class GifsViewModel() : ViewModel(), KoinComponent {
    val retrofitService = StandAloneContext.getKoin().koinContext.get<GiphyApiService>()
    val dao = StandAloneContext.getKoin().koinContext.get<GifDao>()

    var searchQuery: String = ""
        set(value) {
            getGifs(value)
            field = value
        }

    private val _gifs: MutableLiveData<List<Gif>> = MutableLiveData(emptyList())
    val gifs: LiveData<List<Gif>> = _gifs

    var detailScreenPosition: Int = -1


    init {
        getGifs()
    }


    private fun getGifs(query: String = "batman") {
        viewModelScope.launch {
            try {
                val gifs = retrofitService.getApiResponse(query).data.map {
                    val imgUrl = it.images.downsized.url
                    Gif(it.id, imgUrl, query)
                }
                dao.insertGifs(gifs)
            } catch (e: Exception) {

            }
            _gifs.value = dao.getGifs(query)

        }
    }


}