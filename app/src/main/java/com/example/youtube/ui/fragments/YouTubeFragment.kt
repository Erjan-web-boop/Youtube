package com.example.youtube.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.youtube.Resource
import com.example.youtube.ui.adapter.YouTubeAdapter
import com.example.youtube.viewmodel.YouTubeViewModel
import com.example.youtube.databinding.FragmentYoutubeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class YouTubeFragment : BaseFragment<FragmentYoutubeBinding>() {

    private val viewModel by viewModel<YouTubeViewModel>()

    override fun inflateViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentYoutubeBinding {
        return FragmentYoutubeBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = YouTubeAdapter { playlist ->
            openPlaylistDetails(playlist.id)

        }
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.getPlaylists().observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Success -> {
                    adapter.submitList(resource.data)
                }
                is Resource.Error -> {
                }
                is Resource.Loading -> {
                }
            }
        }
        viewModel.getPlaylists()
    }

    private fun openPlaylistDetails(playlistId: String) {
        val action = YouTubeFragmentDirections.actionYouTubeFragmentToPlayListDetailsFragment(playlistId)
        findNavController().navigate(action)
    }
}
