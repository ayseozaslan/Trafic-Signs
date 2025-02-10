package com.example.trafikk.ui.fragment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.trafikk.R
import com.example.trafikk.data.entity.BilgiIsaretleri
import com.example.trafikk.databinding.BilgiIsaretleriCardBinding
import com.example.trafikk.ui.viewmodel.SharedViewModel


class BilgiIsaretleriAdapter(var mContext:Context,var sharedViewModel: SharedViewModel,var isaretlerListesi:List<BilgiIsaretleri>)
    :RecyclerView.Adapter<BilgiIsaretleriAdapter.CardBilgiIsaretleriTutucu>(){

    inner class CardBilgiIsaretleriTutucu(var tasarim:BilgiIsaretleriCardBinding) :RecyclerView.ViewHolder(tasarim.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardBilgiIsaretleriTutucu {
        val binding:BilgiIsaretleriCardBinding=DataBindingUtil.inflate(LayoutInflater.from(parent.context),
        R.layout.bilgi_isaretleri_card,parent,false)
        return CardBilgiIsaretleriTutucu(binding)
    }

    override fun onBindViewHolder(holder: CardBilgiIsaretleriTutucu, position: Int) {
       val bilgiisaretleri=isaretlerListesi.get(position)
        val t=holder.tasarim
        // SpanCount'a göre boyutları ayarla
        val layoutParams = t.cardviewBilgi.layoutParams
        if (sharedViewModel.spanCount.value == 1) {
            // Tek sütun için daha büyük boyut
            layoutParams.width = (holder.itemView.context.resources.displayMetrics.widthPixels * 1.0).toInt()
            layoutParams.height = 850  // Daha büyük yükseklik
            t.imageViewBilgi.layoutParams.width = 920   // Daha büyük genişlik
            t.imageViewBilgi.layoutParams.height = 920  // Daha büyük yükseklik
        } else {
            // İki sütun için biraz daha büyük boyut
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
            layoutParams.height = 550  // Daha büyük yükseklik
            t.imageViewBilgi.layoutParams.width = 620   // Daha büyük genişlik
            t.imageViewBilgi.layoutParams.height = 620  // Daha büyük yükseklik

        }
        t.cardviewBilgi.layoutParams = layoutParams

        val url ="https://ayseozaslan.com/trafiktanzim/isaretler_resimler/${bilgiisaretleri.resim}"

        Glide.with(mContext)
            .load(url)
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .override(400,400)
            .into(t.imageViewBilgi)

        t.bilgiNesnesi=bilgiisaretleri

    }
    override fun getItemCount(): Int {
       return isaretlerListesi.size

    }
}