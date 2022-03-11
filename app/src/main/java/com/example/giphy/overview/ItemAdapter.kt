package com.example.giphy.overview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.giphy.R
import com.example.giphy.databinding.ViewItemBinding
import com.example.giphy.domain.Gif

class ItemAdapter() :
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    var gifList: List<Gif> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(gifList[position], position)

    }

    override fun getItemCount(): Int {
        return gifList.size
    }

    class ItemViewHolder private constructor(private val binding: ViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Gif, position: Int) {
            Glide.with(binding.root).load(item.imgURL).error(R.drawable.ic_broken_image)
                .fallback(R.drawable.loading_animation).diskCacheStrategy(DiskCacheStrategy.ALL).into(binding.ivGif)
        }


        companion object {
            fun create(parent: ViewGroup): ItemViewHolder = ItemViewHolder(
                ViewItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

        }

    }


}