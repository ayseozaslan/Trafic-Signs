package com.example.trafikk.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.trafikk.R
import com.example.trafikk.data.entity.DurmaVeParketme
import com.example.trafikk.databinding.FragmentDurmaVeParketmeBinding
import com.example.trafikk.ui.fragment.adapter.DurmaVeParketmeAdapter
import com.example.trafikk.ui.fragment.viewmodel.DurmaVeParketmeViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.trafikk.ui.viewmodel.SharedViewModel

@AndroidEntryPoint
class DurmaVeParketmeFragment : Fragment() {

    private lateinit var binding:FragmentDurmaVeParketmeBinding
    private lateinit var viewModel:DurmaVeParketmeViewModel
    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_durma_ve_parketme, container, false)
        
        sharedViewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]

        // Sütun sayısı değişikliğini dinle
        sharedViewModel.spanCount.observe(viewLifecycleOwner) { sutunSayisi ->
            val staggeredGridLayoutManager = StaggeredGridLayoutManager(sutunSayisi, StaggeredGridLayoutManager.VERTICAL)
            staggeredGridLayoutManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
            binding.rvDurma.layoutManager = staggeredGridLayoutManager
        }

        binding.rvDurma.setHasFixedSize(true)

        viewModel.durmaListesi.observe(viewLifecycleOwner){
            val durmaVeParketmeAdapter=DurmaVeParketmeAdapter(requireContext(),sharedViewModel,it)
            binding.durmaveparketmeAdapter=durmaVeParketmeAdapter
        }
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val temppviewmodel:DurmaVeParketmeViewModel by viewModels ()
        viewModel=temppviewmodel
    }


}