package com.example.youtube.api


import com.example.youtube.model.YoutubePlaylistsModel
import com.example.youtube.model.YoutubeVideosModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("youtube/v3/playlists")
    suspend fun getPlaylists(
        @Query("part") part: String = "snippet,contentDetails",
        @Query("channelId") channelId: String,
        @Query("maxResults") maxResults: Int = 10,
        @Query("key") apiKey: String
    ): Response<YoutubePlaylistsModel>

    @GET("youtube/v3/search")
    suspend fun searchVideos(
        @Query("part") part: String,
        @Query("q") query: String,
        @Query("type") type: String,
        @Query("key") apiKey: String
    ): Response<YoutubeVideosModel>

    @GET("youtube/v3/playlistItems")
    suspend fun getVideosFromPlaylist(
        @Query("part") part: String = "snippet",
        @Query("playlistId") playlistId: String,
        @Query("maxResults") maxResults: Int = 10,
        @Query("key") apiKey: String
    ): Response<YoutubeVideosModel>
}
