package com.unidy2002.douyin.ui.health

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.unidy2002.douyin.R
import com.unidy2002.douyin.databinding.FragmentHealthBinding

class HealthFragment : Fragment() {

    private var _binding: FragmentHealthBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHealthBinding.inflate(inflater, container, false)
        binding.healthBack.setOnClickListener { activity?.finish() }
        binding.healthExchangeButton.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.navigation_exchange)
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}