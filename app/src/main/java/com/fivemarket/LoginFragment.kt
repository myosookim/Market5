package com.fivemarket

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.fivemarket.databinding.FragmentItemlistTotalBinding
import com.fivemarket.databinding.FragmentLoginBinding
import com.kakao.sdk.common.model.AuthErrorCause.*
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {

    private var _binding : FragmentLoginBinding? = null
    private val binding get() = _binding!!

    lateinit var kakaoCallback: (OAuthToken?, Throwable?) -> Unit

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        kakaoCallback = { token, error ->
            if (error != null) {
                Toast.makeText(context, "로그인 실패ㅠ", Toast.LENGTH_SHORT).show()
            } else if (token != null) {
                findNavController().navigate(R.id.mainScreenFragment)
            }
        }

        binding.kakaoLogin.setOnClickListener {
            handleKakaoLogin()
        }

        binding.btnLogout.setOnClickListener {
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

    private fun handleKakaoLogin() {
        // 카카오 계정으로 로그인 공통 콜백 구성함!
        // 카카오톡으로 로그인 불가해 카카오계정으로 로그인할 경우 사용된다.

        // 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(requireContext())) {
            UserApiClient.instance.loginWithKakaoTalk(requireContext()) { token, error ->
                if (error != null) {
                    Toast.makeText(context, "카카오톡으로 로그인 실패", Toast.LENGTH_SHORT).show()
                    Log.e("카카오톡으로 로그인 실패ㅠㅜ", "$error")

                    // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                    // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                    if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                        return@loginWithKakaoTalk
                    }

                    // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                    UserApiClient.instance.loginWithKakaoAccount(
                        requireContext(),
                        callback = kakaoCallback
                    )
                } else if (token != null) {
                    Toast.makeText(context, "카카오톡으로 로그인 성공", Toast.LENGTH_SHORT).show()
                    Log.e("카카오톡으로 로그인 성공!"," ${token.accessToken}")
                }
            }
        } else {
            UserApiClient.instance.loginWithKakaoAccount(requireContext(), callback = kakaoCallback)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}