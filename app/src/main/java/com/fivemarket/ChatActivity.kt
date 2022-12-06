package com.fivemarket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.fivemarket.databinding.ActivityChatBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class ChatActivity : AppCompatActivity() {

    private lateinit var receiverName: String
    private lateinit var receiverUid:String
    //binding 객체
    private lateinit var binding: ActivityChatBinding

    lateinit var  mAuth:FirebaseAuth //인증 객체
    lateinit var mDbRef: DatabaseReference //DB 객체

    //받는 방과 보내는 방의 대화방 변수
    private lateinit var receiverRoom: String //받는 대화방
    private lateinit var senderRoom: String //보낸 대화방

    private lateinit var messageList : ArrayList<Message>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater) //선언한 객체 초기화
        setContentView(binding.root)

        //초기화
        messageList = ArrayList()
        val messageAdapter: MessageAdapter = MessageAdapter(this, messageList)

        //RecyclerView
        binding.chatRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.chatRecyclerView.adapter=messageAdapter

        //넘어온 데이터 변수를 담는다.->키 값을 넣으면 된다.
        receiverName = intent.getStringExtra("name").toString()
        receiverUid = intent.getStringExtra("uId").toString()

        mAuth = FirebaseAuth.getInstance()
        mDbRef = FirebaseDatabase.getInstance().reference

        //보낸 사람/받는 사람 UID
        val senderUid = mAuth.currentUser?.uid

        //보낸 유저 방
        senderRoom = receiverUid+senderUid

        //받는 유저 방방
       receiverRoom = senderUid+receiverUid


        //액션바에 상대방 이름 보여주기. (투명)
        supportActionBar?.title = receiverName

        //메세지 전송 버튼
        binding.sendBtn.setOnClickListener{

            val message = binding.messageEdit.text.toString() //입력한 메세지 값을 메세지에 넣고
            val messageObject = Message(message, senderUid) // 메세지 클래스 형식의 값은 messageObject에 넣는다.

            //메세지를 데이터 베이스에 저장하기, chats 라는 공간을 만들어 그 안에 sendroom값으로 공간을 생성한다.그리고
            mDbRef.child("chats").child(senderRoom).child("messages").push()//sendRoom 안에 message로 공간을 생성
                .setValue(messageObject).addOnSuccessListener {
                    //저장 성공 시
                    mDbRef.child("chats").child(receiverRoom).child("messages").push()
                        .setValue(messageObject) //receiverRoom안에 전송된 저장
                }
            //입력값 초기화
            binding.messageEdit.setText("")
        }
        //메세지 전송 버튼 기능 구현
        mDbRef.child("chats").child(senderRoom).child("messages")
            .addValueEventListener(object: ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    messageList.clear()

                    for(postSnapshat in snapshot.children){

                        val message = postSnapshat.getValue(Message::class.java)
                        messageList.add(message!!) //snapshot데이터를 postSnapshot에 담아 반복하고 값을 message에 넣는다.
                        //죄종적으로 message를 messageList에 담는다.

                    }
                    //적용
                    messageAdapter.notifyDataSetChanged()//화면에 메세지 내용 출력

                }

                override fun onCancelled(error: DatabaseError) {

                }

            })

    }
}