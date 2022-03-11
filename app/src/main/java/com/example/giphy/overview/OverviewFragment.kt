package com.example.giphy.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.giphy.databinding.FragmentOverviewBinding

class OverviewFragment : Fragment() {

    private val viewModel: GifsViewModel by viewModels()
    private lateinit var adapter: ItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentOverviewBinding.inflate(inflater)
        viewModel.gifs.observe(viewLifecycleOwner) {
            adapter.gifList = it

        }

        adapter = ItemAdapter()
        binding.recyclerView.adapter = adapter
        return binding.root
    }


}