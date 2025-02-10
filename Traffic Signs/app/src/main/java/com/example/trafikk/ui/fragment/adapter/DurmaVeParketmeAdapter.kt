package com.example.trafikk.ui.fragment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.trafikk.R
import com.example.trafikk.data.entity.DurmaVeParketme
import com.example.trafikk.databinding.DurmaVeParketmeCardBinding
import com.example.trafikk.ui.viewmodel.SharedViewModel

class DurmaVeParketmeAdapter(var mContext:Context,var sharedViewModel: SharedViewModel,var durmaListesi:List<DurmaVeParketme>)
    :RecyclerView.Adapter<DurmaVeParketmeAdapter.CardDurmaVeParketmeTutucu>(){

    inner class CardDurmaVeParketmeTutucu(var tasarim:DurmaVeParketmeCardBinding) :RecyclerView.ViewHolder(tasarim.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardDurmaVeParketmeTutucu {
        val binding:DurmaVeParketmeCardBinding=DataBindingUtil.inflate(LayoutInflater.from(parent.context),
        R.layout.durma_ve_parketme_card,parent,false)
        return CardDurmaVeParketmeTutucu(binding)
    }

    override fun onBindViewHolder(holder: CardDurmaVeParketmeTutucu, position: Int) {
       val durmaveparketme=durmaListesi.get(position)
        val t=holder.tasarim

        // SpanCount'a göre boyutları ayarla
        val layoutParams = t.cardviewDurma.layoutParams
        if (sharedViewModel.spanCount.value == 1) {
            // Tek sütun için daha büyük boyut
            layoutParams.width = (holder.itemView.context.resources.displayMetrics.widthPixels * 1.0).toInt()
            layoutParams.height = 850  // Daha büyük yükseklik
            t.imageViewDurma.layoutParams.width = 920  // Daha büyük genişlik
            t.imageViewDurma.layoutParams.height = 920  // Daha büyük yükseklik
        } else {
            // İki sütun için biraz daha büyük boyut
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
            layoutParams.height = 550  // Daha büyük yükseklik
            t.imageViewDurma.layoutParams.width = 620   // Daha büyük genişlik
            t.imageViewDurma.layoutParams.height = 620  // Daha büyük yükseklik
        }
        t.cardviewDurma.layoutParams = layoutParams

        val url ="https://ayseozaslan.com/trafiktanzim/durma_resimler/${durmaveparketme.resim}"
        Glide.with(mContext)
            .load(url)
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .override(400,400)
            .into(t.imageViewDurma)

       t.durmaNesnesi=durmaveparketme

    }
    override fun getItemCount(): Int {
        return durmaListesi.size
    }


}