package com.example.trafikk.ui.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.graphics.toColor
import androidx.fragment.app.FragmentActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.trafikk.R
import com.example.trafikk.databinding.FragmentAnasayfaBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.info.sqlitekullanimihazirveritabani.DatabaseCopyHelper
import com.example.trafikk.ui.viewmodel.SharedViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnasayfaFragment : Fragment() {

    private lateinit var binding:FragmentAnasayfaBinding
    private lateinit var sharedViewModel: SharedViewModel
    private var isGridExpanded = true

    private  val fragmentListesi=ArrayList<Fragment>()  //1
    private val baslikListesi=ArrayList<String>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAnasayfaBinding.inflate(inflater, container, false)
        binding.anasayfaFragment = this

        // SharedViewModel'i başlat
        sharedViewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]

        veritabaniKopyala()
        setupViewPager()
        
        binding.imageViewGoruntu.setOnClickListener {
            isGridExpanded = !isGridExpanded
            sharedViewModel.spanCount.value = if (isGridExpanded) 2 else 1

            if (isGridExpanded){
                binding.imageViewGoruntu.setImageResource(R.drawable.goruntu1)

            }else{
                binding.imageViewGoruntu.setImageResource(R.drawable.goruntu2)
            }

        }

        return binding.root
    }

    private fun setupViewPager() {
        fragmentListesi.add(TrafikTanzimFragment())
        fragmentListesi.add(TehlikeUyariFragment())
        fragmentListesi.add(BilgiIsaretleriFragment())
        fragmentListesi.add(DurmaVeParketmeFragment())

        val adapter = MyviewPagerAdapter(requireActivity())
        binding.viewPager2.adapter = adapter

        baslikListesi.add("Trafik Tanzim")
        baslikListesi.add("Tehlike Uyari")
        baslikListesi.add("Bilgi İsaretleri")
        baslikListesi.add("Durma Ve Parketme")

        TabLayoutMediator(binding.tablayout, binding.viewPager2) { tab, position ->
            tab.setText(baslikListesi[position])
        }.attach()
    }

    inner class MyviewPagerAdapter(FragmentActivity: FragmentActivity) : FragmentStateAdapter(FragmentActivity){  //3
        override fun getItemCount(): Int {
            return fragmentListesi.size
        }

        override fun createFragment(position: Int): Fragment {
            return fragmentListesi[position]
        }
    }


    fun veritabaniKopyala() {
        val copyHelper = DatabaseCopyHelper(requireContext())

        try {
            // Eğer veritabanı varsa sil
            requireContext().deleteDatabase("trafik.sqlite")
            
            // Yeni veritabanını kopyala
            copyHelper.createDataBase()
            copyHelper.openDataBase()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun sinavTikla() {
        AlertDialog.Builder(requireContext(), R.style.AlertDialogTheme)
            .setTitle("Sınav Kategorisi")
            .setMessage("Bu kategoride 15 soru bulunmaktadır. Başarılar")
            .setPositiveButton("Başla") { dialog, _ ->
                Navigation.findNavController(binding.buttonSinav).navigate(R.id.sinavFragment)
                dialog.dismiss()
            }
            .setNegativeButton("Vazgeç") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .apply {
                window?.setBackgroundDrawableResource(R.drawable.alert_dialog_background)
            }
            .show()
    }


}