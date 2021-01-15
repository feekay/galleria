package com.lemonade.ph.views

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.lemonade.ph.R
import com.lemonade.ph.databinding.GalleryFragmentBinding
import com.lemonade.ph.databinding.ViewImageFragmentBinding

class ViewImageFragment : Fragment() {
    private val args: ViewImageFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = ViewImageFragmentBinding.inflate(inflater, container, false)
        binding.imageUrl = args.imageUrl
        return binding.root
    }
}