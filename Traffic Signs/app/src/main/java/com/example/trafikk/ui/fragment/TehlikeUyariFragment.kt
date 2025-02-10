package com.example.trafikk.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.trafikk.R
import com.example.trafikk.databinding.FragmentTehlikeUyariBinding
import com.example.trafikk.databinding.FragmentTrafikTanzimBinding
import com.example.trafikk.ui.fragment.adapter.TehlikeUyariAdapter
import com.example.trafikk.ui.fragment.viewmodel.TehlikeUyariViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.trafikk.ui.viewmodel.SharedViewModel

@AndroidEntryPoint
class TehlikeUyariFragment : Fragment() {

    private lateinit var binding:FragmentTehlikeUyariBinding
    private lateinit var viewModel:TehlikeUyariViewModel
    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tehlike_uyari, container, false)

        // SharedViewModel'i başlat
        sharedViewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]

        // Sütun sayısı değişikliğini dinle
        sharedViewModel.spanCount.observe(viewLifecycleOwner) { sutunSayisi ->
            val staggeredGridLayoutManager = StaggeredGridLayoutManager(sutunSayisi, StaggeredGridLayoutManager.VERTICAL)
            staggeredGridLayoutManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
            binding.rvTehlikeUyari.layoutManager = staggeredGridLayoutManager
        }

        binding.rvTehlikeUyari.setHasFixedSize(true)

        viewModel.uyariListesi.observe(viewLifecycleOwner){
            val tehlikeUyariAdapter = TehlikeUyariAdapter(requireContext(),sharedViewModel, it)
            binding.uyariAdapter = tehlikeUyariAdapter
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempviewModell:TehlikeUyariViewModel by viewModels()
        viewModel = tempviewModell
    }
}