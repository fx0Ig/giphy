package com.example.giphy.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.giphy.domain.Gif
import com.example.giphy.network.ApiResponse
import androidx.lifecycle.viewModelScope
import com.example.giphy.network.GiphyApi
import kotlinx.coroutines.launch


class GifsViewModel : ViewModel() {

    private val _searchQuery: MutableLiveData<String> = MutableLiveData("")
    val searchQuery: LiveData<String> = _searchQuery

    private val _gifs: MutableLiveData<List<Gif>> = MutableLiveData(emptyList())
    val gifs: LiveData<List<Gif>> = _gifs

    var currentPosition: Int = -1


    init {
        getGifs()
    }


    private fun getGifs() {
        viewModelScope.launch {
            _gifs.value = GiphyApi.retrofitService.getApiResponse().data.map {
                val imgUrl = it.images.downsized.url
                Gif(imgUrl)
            }

        }
    }


}