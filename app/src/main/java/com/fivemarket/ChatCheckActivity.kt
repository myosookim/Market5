package com.fivemarket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.fivemarket.databinding.ActivityChatcheckBinding
import com.fivemarket.viewmodel.ChatLogoinActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ChatCheckActivity : AppCompatActivity() {

    lateinit var binding: ActivityChatcheckBinding

    lateinit var mAuth: FirebaseAuth

    private lateinit var mDbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatcheckBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //객체 초기화
        mAuth = Firebase.auth

        //데이터 베이스 초기화
        mDbRef = Firebase.database.reference

        binding.checkBtn.setOnClickListener {

            val name = binding.nameEdit.text.toString().trim()
            val email = binding.emailEdit.text.toString().trim()
            val password = binding.passwordEdit.text.toString().trim()

            signup(name, email, password) // signup함수를 호출해 회원정보를 등록한다.
        }

    }

    //회원가입 기능 넣음
   private  fun signup(name: String, email: String, password: String) {

        mAuth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // 성공시 실행
                    Toast.makeText(this, "채팅 가입 성공", Toast.LENGTH_SHORT).show()
                    val intent: Intent =
                        Intent(this@ChatCheckActivity, chatMainActivity::class.java)
                    startActivity(intent)
                    addUserToDb(name, email, mAuth.currentUser?.uid!!)
                } else {
                    // 실패 시 실행
                    Toast.makeText(this, "채팅 가입 실패", Toast.LENGTH_SHORT).show()
                }

            }
    }

   private fun addUserToDb(name: String, email: String, uId: String){
        mDbRef.child("user").child(uId).setValue(User(name,email,uId ))

    }



}