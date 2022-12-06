package com.fivemarket

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.fivemarket.databinding.ActivityChatMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class chatMainActivity : AppCompatActivity() {

    lateinit var binding: ActivityChatMainBinding
    lateinit var adapter: UserAdapter

    private lateinit var mAuth: FirebaseAuth //인증 서비스 객체
    private lateinit var mDbRef: DatabaseReference //DB 객체

    private lateinit var userList: ArrayList<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatMainBinding .inflate(layoutInflater)
        setContentView(binding.root)

        //객체 초기화
        mAuth = Firebase.auth

        //데이터 베이스 초기화(실시간간
        mDbRef = Firebase.database.reference

        userList = ArrayList()//리스트 초기화

        adapter = UserAdapter(this, userList) //context와 userList를 인자로 넘겼다.

        binding.userRecycelrView.layoutManager = LinearLayoutManager(this)
        binding.userRecycelrView.adapter = adapter

        //데이터 베이스에 있는 유저의 정보를 가져옴
        mDbRef.child("user").addValueEventListener(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot){ //snapshot안에 user의 정보가 들어있고
                for(postSnapshot in snapshot.children){ //데이터를 꺼내 postSnapshot에 넣음

                    //유저(본인) 정보
                    val currentUser = postSnapshot.getValue(User::class.java) //currentUser에 사용자 정보를 넣는다.

                    if(mAuth.currentUser?.uid != currentUser?.uId){ //currentUser에 현재 로그인한 내 정보(uId)가 들어가 있음음
                       userList.add(currentUser!!)
                    }
                }
                adapter.notifyDataSetChanged()//데이터 들어옴
            }
            override fun onCancelled(error: DatabaseError){
                //실패 시 실행

            }
        })


    }
}