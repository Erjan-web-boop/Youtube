package com.example.youtube.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.youtube.Resource
import com.example.youtube.api.ApiService
import com.example.youtube.model.BaseResponse
import com.example.youtube.model.Hello
import kotlinx.coroutines.Dispatchers

class Repository(private val service: ApiService) {
    fun getPlaylists(): LiveData<Resource<List<BaseResponse.Item>>> = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            val response = service.getPlaylists(
                apiKey = "AIzaSyAKMouoKTljTxHeOiooPxpcOlzdLQUgrVA",
                part = "snippet,contentDetails",
                channelId = "UCrd7UM5OBtB0n1zl41qwCUw",
                maxResults = 50
            )
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(Resource.Success(it.items))
                }
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Unknown error"))
        }
    }

    fun getVideosFromPlaylist(playlistId: String, listSize: Int): LiveData<Resource<List<Hello.Item>>> =
        liveData(Dispatchers.IO) {
            emit(Resource.Loading())
            try {
                val response = service.getPlaylistItems(
                    apiKey = "AIzaSyAKMouoKTljTxHeOiooPxpcOlzdLQUgrVA",
                    part = "snippet,contentDetails",
                    playlistId = playlistId,
                    maxResults = listSize
                )
                if (response.isSuccessful) {
                    response.body()?.let {
                        emit(Resource.Success(it.items))
                    }
                }
            } catch (e: Exception) {
                emit(Resource.Error(e.localizedMessage ?: "Unknown error"))
            }
        }
}
