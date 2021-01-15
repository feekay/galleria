package com.lemonade.ph.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.lemonade.ph.models.GalleryPhoto
import com.lemonade.ph.repository.GalleryRepository
import kotlinx.coroutines.flow.Flow

class GalleryViewModel @ViewModelInject constructor(
    private val repository: GalleryRepository
) : ViewModel() {
    var currentQuery: String? = null
        private set

    private var _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private var _isError: MutableLiveData<Boolean> = MutableLiveData()
    val isError: LiveData<Boolean> get() = _isError

    fun searchPictures(queryString: String): Flow<PagingData<GalleryPhoto>> {
        currentQuery = queryString
        return repository.searchPhotos(queryString).cachedIn(viewModelScope)
    }

    fun onLoadEvent(refresh: LoadState) {
        _isLoading.postValue(refresh is LoadState.Loading)
        _isError.postValue(refresh is LoadState.Error)
    }

}