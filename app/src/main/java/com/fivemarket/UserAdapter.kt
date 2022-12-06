package com.fivemarket

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

//RecyclerView.ViewHolder를 상속받은 클래스
//UserAdapter클래스에 UserViewHolder를 적용
class UserAdapter (private val context: Context, private val userList: ArrayList<User>): //context와 ArrayList
    RecyclerView.Adapter<UserAdapter.UserViewHolder>(){ //UserViewHolder를 구현해주어야 한다.


    //user_layout과 연결하는 기능 구현(화면 설정)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view: View = LayoutInflater.from(context). //view객체 안에 user_layout을 넣고 이를 UserViewHolder안에 넣는다.
        inflate(R.layout.user_layout, parent,false)

        return UserViewHolder(view)
    }
    // 데이터 전달받아 user_layout에 보여주는 기능 구현
    override fun onBindViewHolder(holder: UserViewHolder, position: Int){

        val currentUser = userList[position] //userlist안에 있는 데이터를 current에 넣는다.
        holder.nameText.text = currentUser.name //currentUser에 있는 name값을 TextView안에 넣는다.
        holder.itemView.setOnClickListener{
            val intent = Intent(context, ChatActivity::class.java)

            //ChatActivity로 넘어갈 데이터
            intent.putExtra("name",currentUser.name)
            intent.putExtra("uId", currentUser.uId)

            context.startActivity(intent)
        }



    }                //userList에 갯수를 돌려준다.
    override fun getItemCount(): Int { //getItemCount 함수는 userList의 size를 반환환
        return userList.size
    }
    //view변수가 itemView에 들어가 user_layout을 통해 name_text에 접근
    class UserViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val nameText: TextView = itemView.findViewById(R.id.name_text)
    }

}