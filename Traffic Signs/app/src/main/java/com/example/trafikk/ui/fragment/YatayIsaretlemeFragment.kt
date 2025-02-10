package com.example.trafikk.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.trafikk.R
import com.example.trafikk.databinding.FragmentYatayIsaretlemeBinding
import com.example.trafikk.ui.fragment.adapter.YatayIsaretlemeAdapter
import com.example.trafikk.ui.fragment.viewmodel.YatayIsaretlemeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class YatayIsaretlemeFragment : Fragment() {

    private lateinit var binding:FragmentYatayIsaretlemeBinding
    private lateinit var viewModel:YatayIsaretlemeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_yatay_isaretleme,container,false)


        viewModel.yatayisaretlemeListesi.observe(viewLifecycleOwner){
            val yatayisaretlemeAdapter=YatayIsaretlemeAdapter(requireContext(),it)
            binding.yatayisaretlemeAdapter=yatayisaretlemeAdapter
        }
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val temmpVieWModel:YatayIsaretlemeViewModel by viewModels ()
        viewModel=temmpVieWModel
    }


}
