package com.example.youtube.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
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

        viewModel.playlists.observe(viewLifecycleOwner) { playlists ->
            adapter.submitList(playlists)
        }
        viewModel.getPlaylists("UC_x5XG1OV2P6uZZ5FSM9Ttw")
    }

    private fun openPlaylistDetails(playlistId: String) {
        val action = YouTubeFragmentDirections.actionYouTubeFragmentToPlayListDetailsFragment(playlistId)
        findNavController().navigate(action)
    }
}
