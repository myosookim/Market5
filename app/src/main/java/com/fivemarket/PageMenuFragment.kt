package com.fivemarket

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.fivemarket.databinding.FragmentPageMenuBinding

/**
 * A simple [Fragment] subclass.
 * Use the [PageMenuFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PageMenuFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    var binding : FragmentPageMenuBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =  FragmentPageMenuBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.gotoMyprofileBtn?.setOnClickListener {
            findNavController().navigate(R.id.action_pageMenuFragment_to_myprofileFragment)
        }
    }

}