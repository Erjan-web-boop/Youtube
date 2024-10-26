package com.example.youtube.model


import com.google.gson.annotations.SerializedName

data class Hello(
    @SerializedName("etag")
    val etag: String,
    @SerializedName("items")
    val items: List<Item>,
    @SerializedName("kind")
    val kind: String,
    @SerializedName("nextPageToken")
    val nextPageToken: String,
    @SerializedName("pageInfo")
    val pageInfo: PageInfo
) {
    data class Item(
        @SerializedName("contentDetails")
        val contentDetails: ContentDetails,
        @SerializedName("etag")
        val etag: String,
        @SerializedName("id")
        val id: String,
        @SerializedName("kind")
        val kind: String,
        @SerializedName("snippet")
        val snippet: Snippet
    ) {
        data class ContentDetails(
            @SerializedName("itemCount")
            val itemCount: Int
        )

        data class Snippet(
            @SerializedName("channelId")
            val channelId: String,
            @SerializedName("channelTitle")
            val channelTitle: String,
            @SerializedName("description")
            val description: String,
            @SerializedName("localized")
            val localized: Localized,
            @SerializedName("publishedAt")
            val publishedAt: String,
            @SerializedName("thumbnails")
            val thumbnails: Thumbnails,
            @SerializedName("title")
            val title: String
        ) {
            data class Localized(
                @SerializedName("description")
                val description: String,
                @SerializedName("title")
                val title: String
            )

            data class Thumbnails(
                @SerializedName("default")
                val default: Default,
                @SerializedName("high")
                val high: High,
                @SerializedName("maxres")
                val maxres: Maxres,
                @SerializedName("medium")
                val medium: Medium,
                @SerializedName("standard")
                val standard: Standard
            ) {
                data class Default(
                    @SerializedName("height")
                    val height: Int,
                    @SerializedName("url")
                    val url: String,
                    @SerializedName("width")
                    val width: Int
                )

                data class High(
                    @SerializedName("height")
                    val height: Int,
                    @SerializedName("url")
                    val url: String,
                    @SerializedName("width")
                    val width: Int
                )

                data class Maxres(
                    @SerializedName("height")
                    val height: Int,
                    @SerializedName("url")
                    val url: String,
                    @SerializedName("width")
                    val width: Int
                )

                data class Medium(
                    @SerializedName("height")
                    val height: Int,
                    @SerializedName("url")
                    val url: String,
                    @SerializedName("width")
                    val width: Int
                )

                data class Standard(
                    @SerializedName("height")
                    val height: Int,
                    @SerializedName("url")
                    val url: String,
                    @SerializedName("width")
                    val width: Int
                )
            }
        }
    }

    data class PageInfo(
        @SerializedName("resultsPerPage")
        val resultsPerPage: Int,
        @SerializedName("totalResults")
        val totalResults: Int
    )
}