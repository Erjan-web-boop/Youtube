package com.example.youtube.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.youtube.api.ApiService
import com.example.youtube.model.PlaylistItem
import com.example.youtube.model.VideoItem
import com.example.youtube.model.VideoModel
import kotlinx.coroutines.launch

class YouTubeViewModel(private val apiService: ApiService) : ViewModel() {

    private val _playlists = MutableLiveData<List<PlaylistItem>>()
    val playlists: LiveData<List<PlaylistItem>> get() = _playlists

    fun getPlaylists(channelId: String) {
        viewModelScope.launch {
            val response = apiService.getPlaylists(
                channelId = channelId,
                apiKey = "AIzaSyAKMouoKTljTxHeOiooPxpcOlzdLQUgrVA"
            )
            if (response.isSuccessful) {
                _playlists.value = response.body()?.items ?: emptyList()
            } else {
            }
        }
    }

    private val _videos = MutableLiveData<List<VideoItem>>()
    val videos: LiveData<List<VideoItem>> get() = _videos

    fun searchVideos(query: String) {
        viewModelScope.launch {
            val response = apiService.searchVideos(
                part = "snippet",
                query = query,
                type = "video",
                apiKey = "AIzaSyAKMouoKTljTxHeOiooPxpcOlzdLQUgrVA"
            )
            if (response.isSuccessful) {
                _videos.value = response.body()?.items ?: emptyList()
            }else{
                Log.e("YouTubeViewModel", "Error: ${response.errorBody()?.string()}")
            }
        }
    }

    private val video = MutableLiveData<List<VideoModel>>()
    val vadeos: LiveData<List<VideoModel>> get() = video

    fun getVideosFromPlaylist(playlistId: String) {
        viewModelScope.launch {
            val response = apiService.getVideosFromPlaylist(
                playlistId = playlistId,
                apiKey = "AIzaSyAKMouoKTljTxHeOiooPxpcOlzdLQUgrVA"
            )
            if (response.isSuccessful) {
                val videos = response.body()?.items?.map { videoItem ->
                    VideoModel(
                        id = videoItem.id.videoId,
                        title = videoItem.snippet.title,
                        description = videoItem.snippet.description,
                        thumbnailUrl = videoItem.snippet.thumbnails.default.url,
                        publishedAt = videoItem.snippet.publishedAt
                    )
                } ?: emptyList()
                video.value = videos
            } else {
                Log.e("YouTubeViewModel", "Error: ${response.errorBody()?.string()}")
            }
        }
    }

}

