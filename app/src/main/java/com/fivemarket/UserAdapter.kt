package com.fivemarket

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserAdapter (private val context: Context, private val userList: ArrayList<User>):
RecyclerView.Adapter<UserAdapter.UserViewHolder>(){


                    //user_layout과 연결하는 기능 구현
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view: View = LayoutInflater.from(context).
        inflate(R.layout.user_layout, parent,false)

        return UserViewHolder(view)
    }                // 데이터 연결
    override fun onBindViewHolder(holder: UserViewHolder, position: Int){

        val currentUser = userList[position]
        holder.nameText.text = currentUser.name
        holder.itemView.setOnClickListener{

            val intent = Intent(context, ChatActivity::class.java)

            //intent에 넘어갈 데이터를 담는다.
            intent.putExtra("name", currentUser.name)
            intent.putExtra("uId", currentUser.uId)

            context.startActivity(intent)
        }

    }                //userList에 갯수를 돌려준다.
    override fun getItemCount(): Int {
        return userList.size
    }
    class UserViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val nameText: TextView = itemView.findViewById(R.id.name_text)
    }

}
