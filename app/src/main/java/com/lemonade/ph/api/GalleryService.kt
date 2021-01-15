package com.lemonade.ph.api

import com.lemonade.ph.BuildConfig
import com.lemonade.ph.models.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GalleryService {
    @GET("api")
    suspend fun search(
        @Query("q") query: String,
        @Query("page") pageNumber: Int,
        @Query("per_page") pageSize: Int,
        @Query("key") apiKey: String = BuildConfig.PIXABAY_KEY
    ): SearchResponse
}
