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

        //데이터 베이스 초기화
        mDbRef = Firebase.database.reference

        userList = ArrayList()

        adapter = UserAdapter(this, userList) //context와 userList를 넘겼다.

        binding.userRecycelrView.layoutManager = LinearLayoutManager(this)
        binding.userRecycelrView.adapter = adapter

        //유저의 정보를 가져옴
        mDbRef.child("user").addValueEventListener(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot){
                for(postSnapshot in snapshot.children){

                    //유저 정보
                    val currentUser = postSnapshot.getValue(User::class.java)

                    if(mAuth.currentUser?.uid != currentUser?.uId){
                        userList.add(currentUser!!)
                    }
                }
                adapter.notifyDataSetChanged()
            }
            override fun onCancelled(error: DatabaseError){
                //실패 시 실행

            }
        })


    }
}