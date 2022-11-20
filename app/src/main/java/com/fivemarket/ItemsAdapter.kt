package com.fivemarket

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fivemarket.databinding.ListItemsBinding

class ItemsAdapter(val items:ArrayList<Items>):RecyclerView.Adapter<ItemsAdapter.Holder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
            val binding = ListItemsBinding.inflate(LayoutInflater.from(parent.context))
            return Holder(binding)

        }

        override fun onBindViewHolder(holder: Holder, position: Int) {
            holder.bind(items[position])
        }

        override fun getItemCount() = items.size

        class Holder(private val binding:ListItemsBinding) : RecyclerView.ViewHolder(binding.root){
            fun bind(items: Items){
                binding.imgItem.setImageResource(items.img)
                binding.txtName.text = items.name
                binding.txtConame.text = items.co_name
                binding.txtPrice.text = items.price.toString()

                var click_cnt = 1
                binding.btnHeart.setOnClickListener {
                    click_cnt++;
                    if(click_cnt % 2 == 0){
                        binding.btnHeart.setImageResource(R.drawable.heart_24dp_red)
                    }
                    else{
                        binding.btnHeart.setImageResource(R.drawable.heart_24dp_gray)
                    }
                }
            }
        }
}