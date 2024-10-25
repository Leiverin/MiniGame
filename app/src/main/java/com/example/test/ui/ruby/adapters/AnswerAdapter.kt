package com.example.test.ui.ruby.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.test.R
import com.example.test.data.mmkv.MMKVUtils
import com.example.test.data.models.RubyModel
import com.example.test.databinding.ItemAnswerBinding
import com.example.test.databinding.ItemEarnRubyBinding
import com.example.test.databinding.ItemOfferBinding
import com.example.test.utils.pushdown.PushDownAnim

class EarnRubyAdapter: Adapter<EarnRubyAdapter.EarnRubyViewHolder>() {

    private val rubyLevels = mutableListOf<RubyModel>()
    var onClickItem: (Int, RubyModel) -> Unit = { _, _ ->}

    fun submitList(list: MutableList<RubyModel>){
        this.rubyLevels.clear()
        this.rubyLevels.addAll(list)
        notifyDataSetChanged()
    }


    inner class EarnRubyViewHolder(
        private val binding: ItemEarnRubyBinding
    ): ViewHolder(binding.root){
        fun binds(pos: Int, model: RubyModel){
            if (MMKVUtils.getListEarned().contains(model)){
                binding.btnEarn.text = "Đã nhận"
                binding.btnEarn.setBackgroundResource(R.drawable.bg_btn_earn_unactive)
                binding.btnEarn.setTextColor(Color.parseColor("#0000000"))
            } else {
                binding.btnEarn.text = "Nhận"
                binding.btnEarn.setBackgroundResource(R.drawable.bg_btn_earn_active)
                binding.btnEarn.setTextColor(Color.parseColor("#FFF"))
            }
            binding.tvTitle.text = "Vượt qua màn ${model.targetLevel} được nhận"
            binding.tvContent.text = "+${model.point}"
            binding.btnEarn.setOnClickListener {
                onClickItem.invoke(pos, model)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EarnRubyViewHolder {
        return EarnRubyViewHolder(ItemEarnRubyBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = rubyLevels.size

    override fun onBindViewHolder(holder: EarnRubyViewHolder, position: Int) {
        holder.binds(position, rubyLevels[position])
    }

}