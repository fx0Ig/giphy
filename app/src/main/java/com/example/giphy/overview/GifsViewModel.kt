package com.example.giphy.overview

import android.app.DownloadManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.giphy.domain.Gif
import com.example.giphy.network.ApiResponse
import androidx.lifecycle.viewModelScope
import com.example.giphy.network.GiphyApiService
import kotlinx.coroutines.launch
import org.koin.standalone.KoinComponent
import org.koin.standalone.StandAloneContext


class GifsViewModel(val retrofitService : GiphyApiService) : ViewModel(), KoinComponent {


    var searchQuery: String = ""
        set(value) {
            getGifs(value)
            field = value
        }

    private val _gifs: MutableLiveData<List<Gif>> = MutableLiveData(emptyList())
    val gifs: LiveData<List<Gif>> = _gifs

    var detailScreenPosition: Int = -1
    var listScreenPosition: Int = -1


    init {
        getGifs()
    }


    private fun getGifs(query: String = "batman") {
        viewModelScope.launch {
            val gifs = retrofitService.getApiResponse(query).data.map {
                val imgUrl = it.images.downsized.url
                Gif(imgUrl)
            }
            if (gifs.isNotEmpty()) {
                _gifs.value = gifs
            }


        }
    }


}