package com.example.swu_guru

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.swu_guru.databinding.MarketlistLayoutBinding

//물건판매 원해요 페이지 리사이클러뷰 어댑터

class MarketWantListAdapter: RecyclerView.Adapter<WantHolder>() {
    var itemList = mutableListOf<ListLayout>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WantHolder {
        val binding = MarketlistLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WantHolder(binding)
    }

    override fun onBindViewHolder(holder: WantHolder, position: Int) {
        val layouts = itemList.get(position)
        holder.setListlayout(layouts)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}

class WantHolder(val binding: MarketlistLayoutBinding): RecyclerView.ViewHolder(binding.root) {

    //클릭시 해당 타이틀을 intent로 이용해서 넘겨주면서 원해요 상세페이지로 이동

    init {
        binding.root.setOnClickListener {
            val intent = Intent(binding.root.context, MarketWantInfo::class.java)
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

