package com.example.trafikk.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.trafikk.R
import com.example.trafikk.data.datasource.VeritabaniYardimcisi
import com.example.trafikk.data.entity.Sorular
import com.example.trafikk.data.entity.SorularDao
import com.example.trafikk.databinding.FragmentSinavBinding
import android.util.Log

class SinavFragment : Fragment() {

    private lateinit var binding: FragmentSinavBinding
    private lateinit var sorular: ArrayList<Sorular>
    private lateinit var yanlisSecenekler: ArrayList<Sorular>
    private lateinit var dogruSoru: Sorular
    private lateinit var tumSecenekler: HashSet<Sorular>
    private lateinit var vt: VeritabaniYardimcisi

    private var soruSayac = 0
    private var dogruSayac = 0
    private var yanlisSayac = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sinav, container, false)
        binding.sinavfragment = this

        vt = VeritabaniYardimcisi(requireContext())
        sorular = SorularDao().rastgele10BayrakGetir(vt)

        setupClickListeners()
        soruYukle()

        return binding.root
    }

    private fun setupClickListeners() {
        binding.apply {
            buttonA.setOnClickListener { dogruKontrol(it as Button) }
            buttonB.setOnClickListener { dogruKontrol(it as Button) }
            buttonC.setOnClickListener { dogruKontrol(it as Button) }
            buttonD.setOnClickListener { dogruKontrol(it as Button) }
        }
    }

    fun soruYukle() {
        try {
            binding.textViewSoruSayi.text = "${soruSayac + 1}/15"

            dogruSoru = sorular.getOrNull(soruSayac) ?: return

            // Resmi yükle
            val resimId = resources.getIdentifier(
                dogruSoru.soru_resim.lowercase().replace(".png", ""),
                "drawable",
                requireContext().packageName
            )

            // Resmi görünür yap ve ayarla
            binding.imageViewResim.apply {
                visibility = View.VISIBLE
                if (resimId != 0) {
                    setImageResource(resimId)
                    contentDescription = dogruSoru.soru_ad
                } else {
                    setImageResource(R.drawable.gevsek_sev)
                    contentDescription = "Gevşek şev" // Varsayılan resmin açıklaması
                    dogruSoru.soru_ad = "Gevşek şev" // Doğru cevabı güncelle
                    Log.w("SinavFragment", "Resim bulunamadı, varsayılan resim kullanılıyor")
                }
            }

            // Doğru cevabı log'a yaz
            //Log.d("SinavFragment", "Doğru cevap: ${dogruSoru.soru_ad}")

            // Yanlış seçenekleri al ve karıştır
            yanlisSecenekler = SorularDao().rastgele3YanlisSecenekGetir(vt, dogruSoru.soru_id)
            val tumSeceneklerList = mutableListOf<Sorular>().apply {
                add(dogruSoru)
                addAll(yanlisSecenekler)
                shuffle()
            }

            // Butonları güncelle
            binding.apply {
                val buttons = listOf(buttonA, buttonB, buttonC, buttonD)
                buttons.forEachIndexed { index, button ->
                    button.apply {
                        visibility = View.VISIBLE
                        isEnabled = true
                        setBackgroundResource(R.drawable.button_normal)
                        text = tumSeceneklerList[index].soru_ad
                        setTextColor(resources.getColor(android.R.color.black))
                        setPadding(48, 32, 48, 32)
                        textSize = 16f
                    }
                }
            }

            tumSecenekler = HashSet(tumSeceneklerList)

        } catch (e: Exception) {
            Log.e("SinavFragment", "Soru yükleme hatası: ${e.message}")
            e.printStackTrace()
        }
    }

    fun dogruKontrol(button: Button) {
        val buttonYazi = button.text.toString()
        val dogruCevap = dogruSoru.soru_ad

        // Tüm butonları devre dışı bırak
        binding.apply {
            listOf(buttonA, buttonB, buttonC, buttonD).forEach {
                it.isEnabled = false
            }
        }

        if (buttonYazi == dogruCevap) {
            dogruSayac++
            button.apply {
                setBackgroundResource(R.drawable.button_correct)
                setTextColor(resources.getColor(R.color.green))
            }
        } else {
            yanlisSayac++
            button.apply {
                setBackgroundResource(R.drawable.button_wrong)
                setTextColor(resources.getColor(R.color.red))
            }

            // Doğru cevabı göster
            binding.apply {
                when (dogruCevap) {
                    buttonA.text -> buttonA.apply {
                        setBackgroundResource(R.drawable.button_correct)
                        setTextColor(resources.getColor(R.color.green))
                    }
                    buttonB.text -> buttonB.apply {
                        setBackgroundResource(R.drawable.button_correct)
                        setTextColor(resources.getColor(R.color.green))
                    }
                    buttonC.text -> buttonC.apply {
                        setBackgroundResource(R.drawable.button_correct)
                        setTextColor(resources.getColor(R.color.green))
                    }
                    buttonD.text -> buttonD.apply {
                        setBackgroundResource(R.drawable.button_correct)
                        setTextColor(resources.getColor(R.color.green))
                    }
                }
            }
        }

        binding.textViewDogru.text = "Doğru: $dogruSayac"
        binding.textViewYanlis.text = "Yanlış: $yanlisSayac"

        // Sonraki soruya geç
        button.postDelayed({
            if (soruSayac < 14) {
                soruSayac++
                soruYukle()
            } else {
                val gecis = SinavFragmentDirections.sinavdanSonucaGecis(dogruSayac, yanlisSayac)
                Navigation.findNavController(button).navigate(gecis)
            }
        }, 1500)
    }


}