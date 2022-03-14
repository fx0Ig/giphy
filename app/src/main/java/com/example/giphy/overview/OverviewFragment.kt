package com.example.giphy.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.giphy.databinding.FragmentOverviewBinding
import com.example.giphy.network.GiphyApiService
import org.koin.android.ext.android.inject
import android.text.Editable

import android.text.TextWatcher
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*


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
        binding.recyclerView.addOnScrollListener(object : RecyclerView
        .OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                viewModel.detailScreenPosition =
                    (recyclerView.layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition()
            }
        })
        binding.search.addTextChangedListener(
            object : TextWatcher {
                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
                override fun beforeTextChanged(
                    s: CharSequence,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                private var timer: Timer = Timer()
                private val DELAY: Long = 500 // Milliseconds
                override fun afterTextChanged(s: Editable) {
                    timer.cancel()
                    timer = Timer()
                    timer.schedule(
                        object : TimerTask() {
                            override fun run() {
                                viewModel.searchQuery = s.toString()
                            }
                        },
                        DELAY
                    )
                }
            }
        )
        viewModel.gifs.observe(viewLifecycleOwner) {
            adapter.gifList = it
            binding.recyclerView.post { binding.recyclerView.scrollToPosition(viewModel.detailScreenPosition) }
        }

    }

    override fun onItemClick(position: Int) {
        viewModel.detailScreenPosition = position
        val action =
            OverviewFragmentDirections.actionOverviewFragment2ToSecondScreenFragment()
        findNavController().navigate(action)
    }


}