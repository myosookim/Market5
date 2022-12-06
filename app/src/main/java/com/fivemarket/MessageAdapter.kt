package com.fivemarket

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth

class MessageAdapter (private val context: Context, private val messageList: ArrayList<Message>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>(){ //받는 사람과 보낸 사람의 뷰홀더가 2개이므로 RecyclerView로

    //메세지에 따라 어떠한 ViewHolder를 사용할지 정하기 위해 변수 두개 선언
    private val receive = 1 //받는 타입
    private val send = 2 //보내는 타입

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return  if (viewType == 1){//받는 화면
            val view: View = LayoutInflater.from(context).inflate(R.layout.receive,parent,false)
            ReceiveViewHolder(view)
        }else{//보내는 화면 viewTpye이 1이 아닌 경우
            val view: View = LayoutInflater.from(context).inflate(R.layout.send,parent,false)
            SendViewHolder(view)
        }

    }
    //데이터 연결해주는 함수
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //현재 메세지
        val currentMessage = messageList[position]
        //보내는 메세지 데이터
        if(holder.javaClass == SendViewHolder::class.java){
            val viewHolder = holder as SendViewHolder
            viewHolder.sendMessage.text = currentMessage.message
        } else{ //받는 메세지 데이터터
           val viewHolder = holder as ReceiveViewHolder
            viewHolder.receiveMessage.text = currentMessage.message
        }
    }

    override fun getItemCount(): Int {
        return messageList.size

    }


    override fun getItemViewType(position: Int): Int {//

        val currentMessage = messageList[position] //메세지 값

        return if(FirebaseAuth.getInstance().currentUser?.uid.equals(currentMessage.sendId)){ //접속자 uid와 currentMessage의 uid를 비교
            send
        }else{
            receive
        }
    }

    //보낸 사람, 보낸 쪽 View를 전달 받아 객체를 생성
    class SendViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val sendMessage: TextView = itemView.findViewById(R.id.send_message_text)
    }
    //받는 사람, 받는 쪽 view를 전달 받아 객체를 생성
    class ReceiveViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val receiveMessage: TextView = itemView.findViewById(R.id.receive_message_text)
    }

}