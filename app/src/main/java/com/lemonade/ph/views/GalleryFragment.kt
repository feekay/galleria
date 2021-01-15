package com.lemonade.ph.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.lemonade.ph.databinding.GalleryFragmentBinding
import com.lemonade.ph.models.GalleryPhoto
import com.lemonade.ph.viewmodels.GalleryViewModel
import com.lemonade.ph.views.adapters.GalleryAdapter
import com.lemonade.ph.views.adapters.SearchTagAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GalleryFragment : Fragment() {
    private var currentJob: Job? = null
    private val viewModel: GalleryViewModel by viewModels()
    private val adapter:GalleryAdapter = GalleryAdapter(::moveToNext)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = GalleryFragmentBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        setupSearchTags(binding)
        setupPhotoGrid(binding)
        search(getInitialItem())
        return binding.root
    }

    private fun setupSearchTags(binding: GalleryFragmentBinding) {
        binding.tagsList.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.tagsList.adapter =
            SearchTagAdapter(DEFAULT_TAGS, getSelectedPosition(), ::search)
    }

    private fun setupPhotoGrid(binding: GalleryFragmentBinding) {
        binding.photoList.layoutManager = GridLayoutManager(context, 2)
        binding.photoList.adapter = adapter
        adapter.addLoadStateListener{
            viewModel.onLoadEvent(it.source.refresh)
        }
    }

    private fun search(query: String) {
        currentJob?.cancel()
        currentJob = lifecycleScope.launch {
            viewModel.searchPictures(query).collectLatest {
                adapter.submitData(it)
            }
        }
    }

    private fun moveToNext(photo: GalleryPhoto) {
        findNavController().navigate(GalleryFragmentDirections.nextAction(photo.fullImageUrl))
    }

    private fun getSelectedPosition() = DEFAULT_TAGS.indexOf(getInitialItem())

    private fun getInitialItem() = viewModel.currentQuery ?: DEFAULT_TAGS[INITIAL_TAG_POSITION]

    companion object {
        val DEFAULT_TAGS = listOf("All", "Beach", "Pets", "Mountains", "Valley", "Sports")
        const val INITIAL_TAG_POSITION = 0
    }
}