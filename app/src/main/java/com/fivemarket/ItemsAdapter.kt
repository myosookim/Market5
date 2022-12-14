package com.fivemarket

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.fivemarket.databinding.ListItemsBinding

class ItemsAdapter(var items: ArrayList<Items>,var currentFragment: Fragment):RecyclerView.Adapter<ItemsAdapter.Holder>() {

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

        override fun getItemId(position: Int): Long {
             return super.getItemId(position)
        }

        inner class Holder(val binding:ListItemsBinding) : RecyclerView.ViewHolder(binding.root){

            fun bind(mitems: Items?){
                mitems?.let {
                    binding.imgItem.setImageResource(mitems.img)
                    binding.txtName.text = mitems.name
                    binding.txtConame.text = mitems.co_name
                    binding.txtPrice.text = mitems.price.toString()
                    if(mitems.isLiked)
                        binding.btnHeart.setImageResource(R.drawable.heart_24dp_red)
                    else
                        binding.btnHeart.setImageResource(R.drawable.heart_24dp_gray)

                    // ?????? ?????????(?????? ?????????)??? ??????
                    binding.txtName.setOnClickListener {
                        var bundle = Bundle()
                        bundle.putSerializable("item", mitems)
                        Log.e("??????????????? ","${currentFragment}")
                        //currentFragment ?????? it?????? ?????? ?????????, ????????? ???????????? ????????? ?????????????????? ?????? ??? ??????????????????
                        // ????????? ?????? ????????? ???????????? ?????????.
                        currentFragment.findNavController().navigate(R.id.itemDetailFragment, bundle)
                    }
                    // ??? ????????? ?????? ?????? ???????????? ??? ????????? ????????? isLiked??? ?????????
                    binding.btnHeart.setOnClickListener {
                        if(mitems.isLiked == true){
                            binding.btnHeart.setImageResource(R.drawable.heart_24dp_gray)
                            mitems.isLiked = false
                            Log.d("${mitems.name}","??? : ${mitems.isLiked}")
                        }
                        else{
                            binding.btnHeart.setImageResource(R.drawable.heart_24dp_red)
                            mitems.isLiked = true
                            Log.d("${mitems.name}","??? : ${mitems.isLiked}")
                        }
                    }
                }

            }
        }
}