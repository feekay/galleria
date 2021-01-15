package com.lemonade.ph.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.lemonade.ph.api.GalleryService
import com.lemonade.ph.models.GalleryPhoto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

private const val SEARCH_PAGE_SIZE = 20

class DefaultGalleryRepository @Inject constructor(private val service: GalleryService) :
    GalleryRepository {

    override fun searchPhotos(query: String): Flow<PagingData<GalleryPhoto>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = SEARCH_PAGE_SIZE),
            pagingSourceFactory = { GalleryPagingSource(service, query) }
        ).flow
    }
}

interface GalleryRepository {
    fun searchPhotos(query: String): Flow<PagingData<GalleryPhoto>>
}