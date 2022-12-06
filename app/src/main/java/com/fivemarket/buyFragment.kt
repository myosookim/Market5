package com.fivemarket

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.fivemarket.databinding.FragmentBuyBinding
import com.fivemarket.databinding.FragmentHeartPageBinding
import com.fivemarket.viewmodel.ItemViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [buyFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class buyFragment : Fragment() {
    private var _binding: FragmentBuyBinding? = null
    private val binding get() = _binding!!

    private val orderHistoryAdapter = OrderHistoryAdapter()//buyfragment 사용 리사이클러뷰 어뎁터
    private val itemViewModel by activityViewModels<ItemViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBuyBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTodayRecycler()

        orderHistoryAdapter.setData(itemViewModel.orderHistory.value ?: emptyList())
        //?: 널일 경우 빈 리스트를 넣어줌 ->구매 버튼을 눌렀을 때 뷰모델에 있는 리스트에 추가
    }

    // 리사이클러뷰 set
    private fun setTodayRecycler() {
        binding?.orderHistoryRecyclerView?.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding?.orderHistoryRecyclerView?.adapter = orderHistoryAdapter
    }
}
