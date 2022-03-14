package com.example.giphy.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.example.giphy.databinding.OneGifFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class SecondScreenFragment : Fragment() {

    private val viewModel: GifsViewModel by viewModel<GifsViewModel>()
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

        binding.fullscreenRecycler.addOnScrollListener(object : RecyclerView
        .OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                viewModel.detailScreenPosition =
                    (recyclerView.layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition()
            }
        })



        viewModel.gifs.observe(viewLifecycleOwner) {
            adapter.gifList = it
            binding.fullscreenRecycler.scrollToPosition(viewModel.detailScreenPosition)

        }
    }
}