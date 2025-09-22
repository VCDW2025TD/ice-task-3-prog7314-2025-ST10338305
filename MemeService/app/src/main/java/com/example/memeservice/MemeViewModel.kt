package com.example.memeservice

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.memeservice.TenorGif
import com.example.memeservice.TenorApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import com.squareup.moshi.Moshi

class MemeViewModel : ViewModel() {

    private val _memes = MutableStateFlow<List<TenorGif>>(emptyList())
    val memes: StateFlow<List<TenorGif>> = _memes

    private val apiKey = "AIzaSyChGgx8ajjJbu3bZmBWGJE5iQhEGXrwAS8"
    private val api: TenorApiService

    init {
        val moshi = Moshi.Builder().build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://tenor.googleapis.com/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        api = retrofit.create(TenorApiService::class.java)

        loadMemes("funny")
    }

    fun loadMemes(query: String) {
        viewModelScope.launch {
            try {
                val response = api.searchGifs(query, apiKey)
                if (response.isSuccessful) {
                    _memes.value = response.body()?.results ?: emptyList()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
