package com.example.youtube.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.youtube.Resource
import com.example.youtube.model.BaseResponse
import com.example.youtube.model.Hello
import com.example.youtube.repository.Repository

class YouTubeViewModel(private val repository: Repository) : ViewModel() {

    fun getPlaylists(): LiveData<Resource<List<BaseResponse.Item>>> = repository.getPlaylists()

    fun getPlaylistVideo(getId: String, listSize: Int): LiveData<Resource<List<Hello.Item>>> =
        repository.getVideosFromPlaylist(getId, listSize)
}


