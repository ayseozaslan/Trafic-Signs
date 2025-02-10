package com.example.trafikk.ui.fragment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.trafikk.R
import com.example.trafikk.data.entity.Yatayİsaretleme
import com.example.trafikk.databinding.YatayIsaretlemeCardBinding

class YatayIsaretlemeAdapter(var mContext:Context,var yatayisaretlemeListesi:List<Yatayİsaretleme>):
    RecyclerView.Adapter<YatayIsaretlemeAdapter.CardYatayIsaretlemeTutucu>() {

    inner class CardYatayIsaretlemeTutucu(var tasarim:YatayIsaretlemeCardBinding) :RecyclerView.ViewHolder(tasarim.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardYatayIsaretlemeTutucu {
        val binding:YatayIsaretlemeCardBinding=DataBindingUtil.inflate(LayoutInflater.from(parent.context),
        R.layout.yatay_isaretleme_card,parent,false)
        return CardYatayIsaretlemeTutucu(binding)
    }

    override fun getItemCount(): Int {
        return yatayisaretlemeListesi.size
    }

    override fun onBindViewHolder(holder: CardYatayIsaretlemeTutucu, position: Int) {
        val yatayisaretleme=yatayisaretlemeListesi.get(position)
        val t=holder.tasarim
        val url ="https://ayseozaslan.com/trafiktanzim/yatayisaretleme_resim/${yatayisaretleme.resim}"


        Glide.with(mContext)
            .load(url)
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .override(400,400)
            .into(t.imageviewYatayIsaretleme)
        t.yatayisaretlemeNesnesi=yatayisaretleme

    }
}