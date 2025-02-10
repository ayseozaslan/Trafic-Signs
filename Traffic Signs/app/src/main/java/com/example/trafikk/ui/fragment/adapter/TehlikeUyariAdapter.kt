package com.example.trafikk.ui.fragment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.trafikk.R
import com.example.trafikk.data.entity.TehlikeUyari
import com.example.trafikk.databinding.TehlikeUyariCardBinding
import com.example.trafikk.databinding.TrafikTanzimCardBinding
import com.example.trafikk.ui.viewmodel.SharedViewModel

class TehlikeUyariAdapter(var mContext:Context, var sharedViewModel: SharedViewModel, var uyariListesi:List<TehlikeUyari>)
    :RecyclerView.Adapter<TehlikeUyariAdapter.CardTehlikeUyariTutucu>(){


    inner class CardTehlikeUyariTutucu(var tasarim:TehlikeUyariCardBinding) :RecyclerView.ViewHolder(tasarim.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTehlikeUyariTutucu {
        val binding: TehlikeUyariCardBinding=DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.tehlike_uyari_card,parent,false)
        return CardTehlikeUyariTutucu(binding)
    }

    override fun onBindViewHolder(holder: CardTehlikeUyariTutucu, position: Int) {
        val tehlikeuyari = uyariListesi.get(position)
        val t = holder.tasarim

        // SpanCount'a göre boyutları ayarla
        val layoutParams = t.cardviewBilgi.layoutParams
        if (sharedViewModel.spanCount.value == 1) {
            // Tek sütun için daha büyük boyut
            layoutParams.width = (holder.itemView.context.resources.displayMetrics.widthPixels * 1.0).toInt()
            layoutParams.height = 950  // Daha büyük yükseklik
            t.imageViewUyari.layoutParams.width = 900   // Daha büyük genişlik
            t.imageViewUyari.layoutParams.height = 900  // Daha büyük yükseklik
        } else {
            // İki sütun için biraz daha büyük boyut
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
            layoutParams.height = 550  // Daha büyük yükseklik
            t.imageViewUyari.layoutParams.width = 620   // Daha büyük genişlik
            t.imageViewUyari.layoutParams.height = 620  // Daha büyük yükseklik
        }
        t.cardviewBilgi.layoutParams = layoutParams

        val url = "https://ayseozaslan.com/trafiktanzim/uyari_resimler/${tehlikeuyari.resim}"
        
        Glide.with(mContext)
            .load(url)
            .fitCenter()
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .into(t.imageViewUyari)

        t.uyariNesnesi = tehlikeuyari
    }
    override fun getItemCount(): Int {
        return uyariListesi.size
    }

}