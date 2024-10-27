package com.example.test.ui.ruby.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.test.R
import com.example.test.data.mmkv.MMKVUtils
import com.example.test.data.models.RubyModel
import com.example.test.databinding.ItemEarnRubyBinding

class EarnRubyAdapter: Adapter<EarnRubyAdapter.EarnRubyViewHolder>() {

    private val rubyLevels = mutableListOf<RubyModel>()
    var onClickItem: (Int, RubyModel) -> Unit = { _, _ ->}
    private val mListPlayed = MMKVUtils.getListPlayed()

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
                binding.btnEarn.setTextColor(ContextCompat.getColor(binding.btnEarn.context, R.color.black))
            } else {
                binding.btnEarn.text = "Nhận"
                binding.btnEarn.setBackgroundResource(R.drawable.bg_btn_earn_active)
                binding.btnEarn.setTextColor(Color.parseColor("#FFFFFF"))
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