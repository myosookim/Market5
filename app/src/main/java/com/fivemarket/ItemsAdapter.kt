package com.fivemarket

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fivemarket.databinding.ListItemsBinding

class ItemsAdapter(val items:ArrayList<Items>):RecyclerView.Adapter<ItemsAdapter.Holder>() {


        //상속받으면 자동 생성
        //ViewHolder에 쓰일 Layout을 inflate하는 함수
        //ViewGroup의 context를 사용하여 특정 화면에서 구현할 수 있도록 함
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
            val binding = ListItemsBinding.inflate(LayoutInflater.from(parent.context))
            return Holder(binding)
        }


        //상속받으면 자동 생성
        //ViewHolder에서 데이터 묶는 함수가 실행되는 곳
        override fun onBindViewHolder(holder: Holder, position: Int) {
            holder.bind(items[position])
        }

        //상속받으면 자동 생성
        override fun getItemCount() = items.size

        class Holder(private val binding:ListItemsBinding) : RecyclerView.ViewHolder(binding.root){
            fun bind(items: Items){
                binding.imgItem.setImageResource(items.img)
                binding.txtName.text = items.name
                binding.txtConame.text = items.co_name
                binding.txtPrice.text = items.price.toString()
            }
        }
}