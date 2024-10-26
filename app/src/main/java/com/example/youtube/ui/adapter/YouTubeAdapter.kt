package com.example.youtube.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.youtube.databinding.ItemPlaylistBinding
import com.example.youtube.model.BaseResponse



class YouTubeAdapter(private val onItemClick: (BaseResponse.Item) -> Unit) :
    ListAdapter<BaseResponse.Item, YouTubeAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPlaylistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val playlistItem = getItem(position)
        holder.bind(playlistItem)
    }


    class ViewHolder(
        private val binding: ItemPlaylistBinding,
        private val onItemClick: (BaseResponse.Item) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(playlistItem: BaseResponse.Item) {
            binding.title.text = playlistItem.snippet.title
            binding.description.text = "${playlistItem.contentDetails.itemCount} videos"

            val thumbnailUrl = playlistItem.snippet.thumbnails.high.url
            Glide.with(binding.root.context)
                .load(thumbnailUrl)
                .into(binding.thumbnail)

            binding.root.setOnClickListener {
                onItemClick(playlistItem)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<BaseResponse.Item>() {
        override fun areItemsTheSame(oldItem: BaseResponse.Item, newItem: BaseResponse.Item): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: BaseResponse.Item, newItem: BaseResponse.Item): Boolean {
            return oldItem == newItem
        }
    }
}


