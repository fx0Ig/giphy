package com.example.giphy.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.giphy.R
import com.example.giphy.databinding.ViewFullscreenItemBinding
import com.example.giphy.databinding.ViewItemBinding
import com.example.giphy.domain.Gif

class FullScreenItemAdapter() :
    RecyclerView.Adapter<FullScreenItemAdapter.FullScreenItemHolder>() {

    var gifList: List<Gif> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FullScreenItemHolder {
        return FullScreenItemHolder.create(parent)
    }

    override fun onBindViewHolder(holderFullScreen: FullScreenItemHolder, position: Int) {
        holderFullScreen.bind(gifList[position], position)

    }

    override fun getItemCount(): Int {
        return gifList.size
    }

    class FullScreenItemHolder private constructor(private val binding: ViewFullscreenItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Gif, position: Int) {
            Glide.with(binding.root).load(item.imgURL).error(R.drawable.ic_broken_image)
                .fallback(R.drawable.loading_animation).diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.imageScreen)
        }


        companion object {
            fun create(parent: ViewGroup): FullScreenItemHolder = FullScreenItemHolder(
                ViewFullscreenItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

        }

    }


}