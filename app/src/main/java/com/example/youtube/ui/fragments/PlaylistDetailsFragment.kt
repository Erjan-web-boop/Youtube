package com.example.youtube.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.youtube.databinding.FragmentPlaylistDetailsBinding
import com.example.youtube.ui.adapter.VideoAdapter
import com.example.youtube.viewmodel.YouTubeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlaylistDetailsFragment : Fragment() {

    private var _binding: FragmentPlaylistDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModel<YouTubeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlaylistDetailsBinding.inflate(inflater, container, false)

        val playlistId = arguments?.getString("playlistId") ?: ""

        val adapter = VideoAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.getVideosFromPlaylist(playlistId)
        viewModel.vadeos.observe(viewLifecycleOwner) { videos ->
            adapter.submitList(videos)
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
