package com.example.youtube.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.youtube.databinding.ItemVideoBinding
import com.example.youtube.model.Hello



class VideoAdapter : ListAdapter<Hello.Item, VideoAdapter.VideoViewHolder>(VideoDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val binding = ItemVideoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VideoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class VideoViewHolder(private val binding: ItemVideoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(model: Hello.Item) = with(binding){
            tvTitle.text = model.snippet.title
            Glide.with(image).load(model.snippet.thumbnails.default.url).into(image)
        }
    }

    class VideoDiffCallback : DiffUtil.ItemCallback<Hello.Item>() {
        override fun areItemsTheSame(oldItem: Hello.Item, newItem: Hello.Item): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Hello.Item, newItem: Hello.Item): Boolean {
            return oldItem == newItem
        }
    }
}
