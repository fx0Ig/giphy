package com.example.giphy.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.giphy.databinding.FragmentOverviewBinding

class OverviewFragment : Fragment(), GifClickListener {

    private val viewModel: GifsViewModel by activityViewModels()
    private lateinit var binding: FragmentOverviewBinding
    private lateinit var adapter: ItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOverviewBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ItemAdapter(this)
        binding.recyclerView.adapter = adapter
        viewModel.gifs.observe(viewLifecycleOwner) {
            adapter.gifList = it
        }

    }

    override fun onItemClick(position: Int) {
        viewModel.currentPosition = position
        val action =
            OverviewFragmentDirections.actionOverviewFragment2ToSecondScreenFragment()
        findNavController().navigate(action)
    }


}