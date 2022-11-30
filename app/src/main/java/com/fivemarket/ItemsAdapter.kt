package com.fivemarket

import android.content.ClipData.Item
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.fivemarket.databinding.ListItemsBinding
import com.fivemarket.viewmodel.ItemViewModel

// 매개변수를 MutableLiveData 형식으로 받아오면 화면 안 뜸
class ItemsAdapter(var items: ArrayList<Items>):RecyclerView.Adapter<ItemsAdapter.Holder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
            val binding = ListItemsBinding.inflate(LayoutInflater.from(parent.context))
            return Holder(binding)
        }

        override fun onBindViewHolder(holder: Holder, position: Int) {
            holder.bind(items[position])
        }

        override fun getItemCount(): Int{
            return items.size
        }

        fun setHeartData(s_items: ArrayList<Items>){
            items = s_items
            notifyDataSetChanged()
        }

        class Holder(private val binding:ListItemsBinding) : RecyclerView.ViewHolder(binding.root){
            fun bind(items: Items){
                binding.imgItem.setImageResource(items.img)
                binding.txtName.text = items.name
                binding.txtConame.text = items.co_name
                binding.txtPrice.text = items.price.toString()
                if(items.isLiked)
                    binding.btnHeart.setImageResource(R.drawable.heart_24dp_red)
                else
                    binding.btnHeart.setImageResource(R.drawable.heart_24dp_gray)

                // 각 목록의 하트 버튼 클릭했을 때 이미지 바꾸고 isLiked값 바꾸기
                binding.btnHeart.setOnClickListener {
                    if(items.isLiked == true){
                        binding.btnHeart.setImageResource(R.drawable.heart_24dp_gray)
                        items.isLiked = false
                        Log.d("${items.name}","찜 : ${items.isLiked}")
                    }
                    else{
                        binding.btnHeart.setImageResource(R.drawable.heart_24dp_red)
                        items.isLiked = true
                        Log.d("${items.name}","찜 : ${items.isLiked}")
                    }
                }
            }
        }
}