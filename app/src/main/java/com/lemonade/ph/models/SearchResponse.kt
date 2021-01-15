package com.lemonade.ph.models

import com.squareup.moshi.Json
import java.io.Serializable

data class SearchResponse(
    @Json(name = "total") val total: Int,
    @Json(name = "hits") val photos: List<GalleryPhoto>
) : Serializable


data class GalleryPhoto(
    @Json(name = "id") val id: Double,
    @Json(name = "user") val user: String,
    @Json(name = "previewURL") val previewUrl: String,
    @Json(name = "largeImageURL") val fullImageUrl: String
) : Serializable