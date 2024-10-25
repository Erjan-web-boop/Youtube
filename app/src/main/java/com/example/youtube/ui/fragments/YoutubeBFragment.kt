package com.example.youtube.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.youtube.viewmodel.YouTubeViewModel
import com.example.youtube.databinding.FragmentYoutubeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class YouTubeBFragment : BaseFragment<FragmentYoutubeBinding>() {
    private val viewModel by viewModel<YouTubeViewModel>()

    override fun inflateViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentYoutubeBinding {
        return FragmentYoutubeBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.videos.observe(viewLifecycleOwner) { videos ->
        }

        viewModel.searchVideos("Android Development")
    }
}
