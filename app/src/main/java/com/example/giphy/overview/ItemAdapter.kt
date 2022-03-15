package com.example.giphy.overview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.giphy.R
import com.example.giphy.databinding.ViewItemBinding
import com.example.giphy.domain.Gif

class ItemAdapter(val listener: GifClickListener) :
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    var gifList: List<Gif> = listOf()
        set(value) {
            field = value
            notifyItemRangeChanged(0, value.lastIndex)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder.create(parent, listener)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(gifList[position], position)

    }

    override fun getItemCount(): Int {
        return gifList.size
    }

    class ItemViewHolder private constructor(
        private val binding: ViewItemBinding,
        val listener: GifClickListener
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Gif, position: Int) {
            Glide.with(binding.root).load(item.imgURL).error(R.drawable.ic_broken_image)
                .fallback(R.drawable.loading_animation).diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.ivGif)

            binding.ivGif.setOnClickListener {
                listener.onItemClick(position)
            }
        }


        companion object {
            fun create(parent: ViewGroup, listener: GifClickListener): ItemViewHolder =
                ItemViewHolder(
                    ViewItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ), listener
                )

        }

    }


}

interface GifClickListener {
    fun onItemClick(position: Int)

}