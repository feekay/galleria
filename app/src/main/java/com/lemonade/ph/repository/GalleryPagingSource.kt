package com.lemonade.ph.repository

import androidx.paging.PagingSource
import com.lemonade.ph.api.GalleryService
import com.lemonade.ph.models.GalleryPhoto

private const val STARTING_PAGE_INDEX = 1

class GalleryPagingSource(
    private val service: GalleryService,
    private val query: String
) : PagingSource<Int, GalleryPhoto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GalleryPhoto> {
        val page = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = service.search(query, page, params.loadSize)
            val photos = response.photos
            LoadResult.Page(
                data = photos,
                prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1,
                nextKey = if (photos.isEmpty()) null else page + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
}