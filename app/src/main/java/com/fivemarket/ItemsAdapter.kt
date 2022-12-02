package com.fivemarket

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.fivemarket.databinding.ListItemsBinding
import com.fivemarket.viewmodel.ItemViewModel

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

        fun setData(newitems: ArrayList<Items>){
            items = newitems
            notifyDataSetChanged()
        }

        inner class Holder(val binding:ListItemsBinding) : RecyclerView.ViewHolder(binding.root){
            fun bind(items: Items?){
                items?.let {
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
                            //이 아래부턴 발악 코드... (viewmodel의 heartlist가 1도 변경 안됨)
                            ItemViewModel().deleteHeartedItems(items)
                            notifyDataSetChanged()
                            Log.e("${ItemViewModel().heartlist}","사이즈 : ${ItemViewModel().heartlist.size}")
                            Log.d("${items.name}","찜 : ${items.isLiked}")
                        }
                        else{
                            binding.btnHeart.setImageResource(R.drawable.heart_24dp_red)
                            items.isLiked = true
                            //이 아래부턴 발악 코드... (viewmodel의 heartlist가 1도 변경 안됨)
                            ItemViewModel().addHeartedItems(items)
                            notifyDataSetChanged()
                            Log.e("${ItemViewModel().heartlist}","사이즈 : ${ItemViewModel().heartlist.size}")
                            Log.d("${items.name}","찜 : ${items.isLiked}")
                        }
                    }
                }

            }
        }
}