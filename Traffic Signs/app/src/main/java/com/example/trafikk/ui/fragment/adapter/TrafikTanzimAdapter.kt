package com.example.trafikk.ui.fragment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ScaleGestureDetector
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.trafikk.R
import com.example.trafikk.data.entity.TrafikTanzim
import com.example.trafikk.databinding.TrafikTanzimCardBinding
import com.example.trafikk.ui.fragment.viewmodel.TrafikTanzimViewModel
import com.example.trafikk.ui.viewmodel.SharedViewModel

class TrafikTanzimAdapter(var mContext:Context, var sharedViewModel: SharedViewModel, var trafikListesi:List<TrafikTanzim>)
    :RecyclerView.Adapter<TrafikTanzimAdapter.CardTrafikTanzimTutucu>(){




    inner class CardTrafikTanzimTutucu(var tasarim:TrafikTanzimCardBinding) :RecyclerView.ViewHolder(tasarim.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTrafikTanzimTutucu {
        val binding:TrafikTanzimCardBinding=DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.trafik_tanzim_card,parent,false)
        return CardTrafikTanzimTutucu(binding)
    }

    override fun onBindViewHolder(holder: CardTrafikTanzimTutucu, position: Int) {
        val trafiktanzim = trafikListesi.get(position)
        val t = holder.tasarim

        // SpanCount'a göre boyutları ayarla
        val layoutParams = t.cardviewTrafik.layoutParams
        if (sharedViewModel.spanCount.value == 1) {
            // Tek sütun için daha büyük boyut
            layoutParams.width = (holder.itemView.context.resources.displayMetrics.widthPixels * 1.0).toInt()
            layoutParams.height = 950  // Daha büyük yükseklik
            t.imageviewTrafik.layoutParams.width = 900   // Daha büyük genişlik
            t.imageviewTrafik.layoutParams.height = 900  // Daha büyük yükseklik
        } else {
            // İki sütun için biraz daha büyük boyut
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
            layoutParams.height = 550  // Daha büyük yükseklik
            t.imageviewTrafik.layoutParams.width = 620   // Daha büyük genişlik
            t.imageviewTrafik.layoutParams.height = 620  // Daha büyük yükseklik
        }
        t.cardviewTrafik.layoutParams = layoutParams


        val url = "https://ayseozaslan.com/trafiktanzim/tanzim_resimler/${trafiktanzim.resim}"
        
        Glide.with(mContext)
            .load(url)
            .fitCenter()
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .into(t.imageviewTrafik)

        t.tanzimNesnesi = trafiktanzim
    }
    override fun getItemCount(): Int {
        return trafikListesi.size
    }


}