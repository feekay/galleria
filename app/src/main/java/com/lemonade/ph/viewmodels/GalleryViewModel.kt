package com.lemonade.ph.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.lemonade.ph.models.GalleryPhoto
import com.lemonade.ph.repository.GalleryRepository
import kotlinx.coroutines.flow.Flow

class GalleryViewModel @ViewModelInject constructor(
    private val repository: GalleryRepository
) : ViewModel() {
    private var currentQuery: String? = null
    private var currentSearchResult: Flow<PagingData<GalleryPhoto>>? = null


    fun searchPictures(queryString: String): Flow<PagingData<GalleryPhoto>> {
        currentQuery = queryString
        return repository.searchPhotos(queryString).cachedIn(viewModelScope).also {
            currentSearchResult = it
        }
    }
}