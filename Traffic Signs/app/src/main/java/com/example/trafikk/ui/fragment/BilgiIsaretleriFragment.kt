package com.example.trafikk.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.trafikk.R
import com.example.trafikk.databinding.FragmentBilgiIsaretleriBinding
import com.example.trafikk.ui.fragment.adapter.BilgiIsaretleriAdapter
import com.example.trafikk.ui.fragment.viewmodel.BilgiIsaretleriViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.trafikk.ui.viewmodel.SharedViewModel

@AndroidEntryPoint
class BilgiIsaretleriFragment : Fragment() {

    private lateinit var binding: FragmentBilgiIsaretleriBinding
    private lateinit var viewModel: BilgiIsaretleriViewModel
    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_bilgi_isaretleri, container, false)
        
        sharedViewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]

        // Sütun sayısı değişikliğini dinle
        sharedViewModel.spanCount.observe(viewLifecycleOwner) { sutunSayisi ->
            val staggeredGridLayoutManager = StaggeredGridLayoutManager(sutunSayisi, StaggeredGridLayoutManager.VERTICAL)
            staggeredGridLayoutManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
            binding.rvBilgiisaretleri.layoutManager = staggeredGridLayoutManager
        }

        binding.rvBilgiisaretleri.setHasFixedSize(true)

        viewModel.isaretlerListesi.observe(viewLifecycleOwner){
            val bilgiIsaretleriAdapter=BilgiIsaretleriAdapter(requireContext(),sharedViewModel,it)
            binding.bilgiisaretleriAdapter=bilgiIsaretleriAdapter
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempviewmodellll:BilgiIsaretleriViewModel by viewModels ()
        viewModel=tempviewmodellll
    }

}