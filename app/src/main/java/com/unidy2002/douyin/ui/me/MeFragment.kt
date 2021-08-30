package com.unidy2002.douyin.ui.me

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.unidy2002.douyin.R
import com.unidy2002.douyin.databinding.FragmentMeBinding

class MeFragment : Fragment() {

    private var _binding: FragmentMeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMeBinding.inflate(inflater, container, false)
        binding.meIconLeft.setOnClickListener { }
        binding.meIconMiddle.setOnClickListener { }
        binding.meIconRight.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.navigation_health)
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}