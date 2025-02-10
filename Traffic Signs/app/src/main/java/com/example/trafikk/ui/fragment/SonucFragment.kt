package com.example.trafikk.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.trafikk.R
import com.example.trafikk.databinding.FragmentSonucBinding
import androidx.activity.OnBackPressedCallback

class SonucFragment : Fragment() {

    private lateinit var binding: FragmentSonucBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sonuc, container, false)

        binding.sonucfragment = this

        val bundle: SonucFragmentArgs by navArgs()
        val gelendogruSayac = bundle.dogruSayac
        val gelenyanlisSayac = bundle.yanlisSayac

        // Geri tuşunu yönetmek için callback oluştur
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Geri tuşuna basıldığında hiçbir şey yapma
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)

        binding.textViewSonuc.text = "$gelendogruSayac DOĞRU ${15-gelendogruSayac} YANLIŞ"
        //binding.textViewYuzdeSonuc.text = "% ${(gelenyanlisSayac*150/100)} BAŞARI"
        return binding.root
        /*
        val gecis = SinavFragmentDirections.sinavdanSonucaGecis(dogruSayac, yanlisSayac)
                Navigation.findNavController(button).navigate(gecis)

binding.buttonTekrar.setOnClickListener {
    val geciss=SonucFragmentDirections.sonucToSinav()
    Navigation.findNavController(binding.buttonTekrar).navigate(geciss)
}
        binding.buttonanasayfa.setOnClickListener {
            val gecis=SonucFragmentDirections.sonucToAnasayfa()
            Navigation.findNavController(binding.buttonanasayfa).navigate(gecis)
        }
        */


    }

    fun tekrardene(){
        Navigation.findNavController(binding.buttonTekrar).navigate(R.id.sonucToSinav)
    }
    fun anasayfayadon(){
        Navigation.findNavController(binding.buttonAnasayfa).navigate(R.id.sonucToAnasayfa)
    }

}