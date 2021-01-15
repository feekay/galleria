package com.lemonade.ph.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.lemonade.ph.databinding.GalleryFragmentBinding
import com.lemonade.ph.models.GalleryPhoto
import com.lemonade.ph.viewmodels.GalleryViewModel
import com.lemonade.ph.views.adapters.GalleryAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GalleryFragment : Fragment() {


    private var currentJob: Job? = null
    private val viewModel: GalleryViewModel by viewModels()
    private val adapter = GalleryAdapter(::moveToNext)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = GalleryFragmentBinding.inflate(inflater, container, false)
        binding.photoList.layoutManager = GridLayoutManager(context, 2)
        binding.photoList.adapter = adapter
        search("")
        return binding.root
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

}