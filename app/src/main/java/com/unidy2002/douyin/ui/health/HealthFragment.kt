package com.unidy2002.douyin.ui.health

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.unidy2002.douyin.R
import com.unidy2002.douyin.databinding.FragmentHealthBinding
import java.util.*

class HealthFragment : Fragment() {

    private var _binding: FragmentHealthBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private fun show(view: View, delay: Long, duration: Long) {
        Timer(true).schedule(object : TimerTask() {
            override fun run() {
                activity?.runOnUiThread {
                    try {
                        view.animate().alpha(1f).duration = duration
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        }, delay)
    }

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

        show(binding.healthHeaderContainer, 0, 1000)
        show(binding.healthBarTitle, 1000, 1000)
        show(binding.healthBarGraph, 2000, 1000)
        show(binding.healthLineTitle, 3000, 1000)
        show(binding.healthLineGraph, 4000, 1000)
        show(binding.healthReportTitle, 5200, 1000)
        show(binding.healthReportSubtitle, 6200, 1000)
        show(binding.healthReportRow1, 7200, 1000)
        show(binding.healthReportRow2, 8200, 1000)
        show(binding.healthReportRow3, 9200, 1000)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}