package com.example.trafikk.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.trafikk.R
import com.example.trafikk.data.entity.TrafikTanzim
import com.example.trafikk.databinding.FragmentTrafikTanzimBinding
import com.example.trafikk.ui.fragment.adapter.TrafikTanzimAdapter
import com.example.trafikk.ui.fragment.viewmodel.TrafikTanzimViewModel
import dagger.hilt.android.AndroidEntryPoint
import com.bumptech.glide.Glide
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.lifecycle.ViewModelProvider
import com.example.trafikk.ui.viewmodel.SharedViewModel

@AndroidEntryPoint
class TrafikTanzimFragment : Fragment() {

    private lateinit var binding: FragmentTrafikTanzimBinding
    private lateinit var viewModel: TrafikTanzimViewModel
    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_trafik_tanzim, container, false)

        // SharedViewModel'i başlat
        sharedViewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]

        // Sütun sayısı değişikliğini dinle
        sharedViewModel.spanCount.observe(viewLifecycleOwner) { sutunSayisi ->
            val staggeredGridLayoutManager = StaggeredGridLayoutManager(sutunSayisi, StaggeredGridLayoutManager.VERTICAL)
            staggeredGridLayoutManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
            binding.rvTrafikTanzim.layoutManager = staggeredGridLayoutManager
        }

        binding.rvTrafikTanzim.setHasFixedSize(true)

        viewModel.trafikListesi.observe(viewLifecycleOwner) { trafikList ->
            val adapter = TrafikTanzimAdapter(requireContext(),sharedViewModel ,trafikList)
            binding.rvTrafikTanzim.adapter = adapter
            binding.tanzimAdapter = adapter
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel:TrafikTanzimViewModel by viewModels()
        viewModel = tempViewModel
        
        // Glide önbelleğini temizle
       /* Glide.get(requireContext()).clearMemory()
        Thread {
            Glide.get(requireContext()).clearDiskCache()
        }.start()

        */
    }
}