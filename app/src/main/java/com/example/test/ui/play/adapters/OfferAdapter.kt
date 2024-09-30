package com.example.test.ui.play.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.test.databinding.ItemOfferBinding
import com.example.test.utils.pushdown.PushDownAnim

class OfferAdapter: Adapter<OfferAdapter.OfferViewHolder>() {

    private val offers = mutableListOf<String>()

    fun submitList(list: MutableList<String>){
        this.offers.clear()
        this.offers.addAll(list)
        notifyDataSetChanged()
    }

    var onClickItem : (Int, String) -> Unit = { _, _ -> }

    inner class OfferViewHolder(
        private val binding: ItemOfferBinding
    ): ViewHolder(binding.root){
        fun binds(position: Int, s: String){
            binding.tvName.text = s.toString()
            binding.btnOffer.setOnClickListener {
                if (s != ""){
                    onClickItem.invoke(position, s)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferViewHolder {
        return OfferViewHolder(ItemOfferBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = offers.size

    override fun onBindViewHolder(holder: OfferViewHolder, position: Int) {
        holder.binds(position, offers[position])
    }

}