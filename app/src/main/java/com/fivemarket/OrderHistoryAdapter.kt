package com.fivemarket

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fivemarket.databinding.ItemOrderHistoryBinding

class OrderHistoryAdapter : RecyclerView.Adapter<OrderHistoryAdapter.ViewHolder>() {
    var orderHistoryList: List<Items> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemOrderHistoryBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(orderHistoryList[position])
    }

    override fun getItemCount(): Int = orderHistoryList.size

    class ViewHolder(private val binding: ItemOrderHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(items: Items) {
            binding.imgItem.setImageResource(items.img)
            binding.txtName.text = items.name
            binding.txtConame.text = items.co_name
            binding.shippingStatus.text = "진행중" // TODO
            binding.priceBtn.text = items.price.toString()
        }
    }


    fun setData(list: List<Items>) {
        orderHistoryList = list
        notifyDataSetChanged()
    }
}