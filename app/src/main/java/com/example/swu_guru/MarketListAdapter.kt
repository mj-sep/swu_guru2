package com.example.swu_guru

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.swu_guru.databinding.MarketlistLayoutBinding

//물건판매 리사이클러뷰 어댑터

class ListAdapter: RecyclerView.Adapter<Holder>() {
    var itemList = mutableListOf<ListLayout>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = MarketlistLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val layouts = itemList.get(position)
        holder.setListlayout(layouts)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}

class Holder(val binding: MarketlistLayoutBinding): RecyclerView.ViewHolder(binding.root) {

    //클릭시 해당 타이틀을 intent로 이용해서 넘겨주면서 팔아요 상세페이지로 이동
    init {
        binding.root.setOnClickListener {
            val intent = Intent(binding.root.context, MarketInfo::class.java)
            intent.putExtra("market_title", binding.marketTitle.text)
            binding.root.context.startActivity(intent)
        }
    }

    //보여줄 정보 : 제목과 가격
    fun setListlayout(layouts: ListLayout) {
        binding.marketTitle.text = "${layouts.title}"
        binding.marketCost.text = "${layouts.cost}"
    }

}