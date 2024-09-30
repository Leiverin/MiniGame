package com.example.test.ui.play.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.test.databinding.ItemAnswerBinding
import com.example.test.databinding.ItemOfferBinding
import com.example.test.utils.pushdown.PushDownAnim

class AnswerAdapter: Adapter<AnswerAdapter.AnswerViewHolder>() {

    private val offers = mutableListOf<String>()
    var onClickItem: (Int, String) -> Unit = { _, _ ->}

    fun submitList(list: MutableList<String>){
        this.offers.clear()
        this.offers.addAll(list)
        notifyDataSetChanged()
    }


    inner class AnswerViewHolder(
        private val binding: ItemAnswerBinding
    ): ViewHolder(binding.root){
        fun binds(pos: Int, s: String){
            binding.tvName.text = s
            binding.btnOffer.setOnClickListener {
                if (s != ""){
                    onClickItem.invoke(pos, s)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerViewHolder {
        return AnswerViewHolder(ItemAnswerBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = offers.size

    override fun onBindViewHolder(holder: AnswerViewHolder, position: Int) {
        holder.binds(position, offers[position])
    }

}