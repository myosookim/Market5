package com.fivemarket.viewmodel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View

import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.fivemarket.ChatCheckActivity
import com.fivemarket.MainActivity
import com.fivemarket.MyprofileFragment
import com.fivemarket.R
import com.fivemarket.databinding.ActivityChatLogoinBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ChatLogoinActivity : AppCompatActivity() {

    lateinit var binding:ActivityChatLogoinBinding

    lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatLogoinBinding.inflate(layoutInflater)
        setContentView(binding.root)
       //프로필 채팅 누르면 여기로 이동
        //supportActionBar!!.setTitle("chatting")
        //supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        //객체 초기화
        mAuth = Firebase.auth

        //채팅 시작 이벤트
        binding.chatStartBtn.setOnClickListener{
            val email = binding.emailEdit.text.toString()
            val password = binding.passwordEdit.text. toString()

            chatstart(email, password)
        }



        //회원 정보 기입 버튼 이벤트
        binding.checkBtn.setOnClickListener{
            val intent: Intent = Intent(this@ChatLogoinActivity,ChatCheckActivity::class.java)
            startActivity(intent)
        }

        // 툴바 (액션바 비활성화했기 때문에, 커스텀한 툴바를 액션바 대신 사용).
        // 주의!! : 툴바 코드는 onCreate의 제일 마지막 부분에 있어야 합니다.
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
    }

    private fun chatstart(email: String, password: String){
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    //성공 시 실행
                    val intent: Intent = Intent(this@ChatLogoinActivity, MainActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(this, "채팅 시작", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    // 실패 시 실행
                    Toast.makeText(this, "채팅 시작 실패", Toast.LENGTH_SHORT).show()

                }
            }

    }




}