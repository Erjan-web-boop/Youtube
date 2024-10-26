package com.example.youtube.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.youtube.Resource
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

        val adapter = VideoAdapter()
        binding.rvPlaylistItems.adapter = adapter
        binding.rvPlaylistItems.layoutManager = LinearLayoutManager(requireContext())

        val getId = arguments?.getString("id") ?: ""
        val getSize = arguments?.getInt("size") ?: 0


        viewModel.getPlaylistVideo(getId,getSize).observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Success -> {
                    adapter.submitList(resource.data)
                }
                is Resource.Error -> {
                    Log.e("PlaylistDetailsFragment", "Error: ${resource.message}")
                }
                is Resource.Loading -> {
                }
            }
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
