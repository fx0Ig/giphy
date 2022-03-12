package com.example.giphy.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.example.giphy.databinding.OneGifFragmentBinding


class SecondScreenFragment : Fragment() {

    private val viewModel: GifsViewModel by activityViewModels()
    private lateinit var binding: OneGifFragmentBinding
    private lateinit var adapter: FullScreenItemAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = OneGifFragmentBinding.inflate(inflater)


        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val snapHelper: SnapHelper = PagerSnapHelper()
        adapter = FullScreenItemAdapter()
        binding.fullscreenRecycler.adapter = adapter
        snapHelper.attachToRecyclerView(binding.fullscreenRecycler)

        viewModel.gifs.observe(viewLifecycleOwner) {
            adapter.gifList = it
            binding.fullscreenRecycler.scrollToPosition(viewModel.currentPosition)

        }
    }
}