package com.example.youtube.model


data class PlaylistItem(
    val id: String,
    val snippet: Snippet,
    val contentDetails: ContentDetails
)

data class Snippet(
    val title: String,
    val description: String,
    val thumbnails: Thumbnails
)

data class ContentDetails(
    val itemCount: Int
)

data class Thumbnails(
    val default: Thumbnail,
    val medium: Thumbnail,
    val high: Thumbnail
)

data class Thumbnail(
    val url: String
)

data class YoutubePlaylistsModel(
    val items: List<PlaylistItem>
)

data class VideoItem(
    val id: VideoId,
    val snippet: VideoSnippet
)


data class VideoId(
    val videoId: String
)

data class VideoSnippet(
    val title: String,
    val description: String,
    val thumbnails: Thumbnails,
    val publishedAt: String
)

data class YoutubeVideosModel(
    val items: List<VideoItem>
)

data class VideoModel(
    val id: String,
    val title: String,
    val description: String,
    val thumbnailUrl: String,
    val publishedAt: String
)
