package com.fivemarket

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.fivemarket.databinding.FragmentMyprofileBinding
import com.fivemarket.viewmodel.ChatLogoinActivity
import com.kakao.sdk.user.UserApiClient

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MyprofileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MyprofileFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var _binding : FragmentMyprofileBinding? = null
    private val binding get() = _binding!!

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)

        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val bind = FragmentMyprofileBinding.inflate(layoutInflater)
        //프로필 채팅 버튼 클릭시 채팅 로그인으로 이동
        bind.chatting . setOnClickListener{
            val intent = Intent(this@MyprofileFragment.requireContext(), ChatLogoinActivity::class.java)
            startActivity(intent)

        }

        binding = FragmentMyprofileBinding.inflate(inflater)
        return binding.root
        return bind.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.kakaologout.setOnClickListener {
            UserApiClient.instance.logout { error ->
                if (error != null) {
                    Toast.makeText(context, "카카오 로그아웃 실패", Toast.LENGTH_SHORT).show()
                    Log.d("카카오","카카오 로그아웃 실패ㅠㅠ")
                }else {
                    Toast.makeText(context, "카카오 로그아웃 성공", Toast.LENGTH_SHORT).show()
                    Log.d("카카오","카카오 로그아웃 성공!")
                }
            }
        }
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MyprofileFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MyprofileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}