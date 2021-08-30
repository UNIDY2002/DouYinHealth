package com.unidy2002.douyin.ui.health

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
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

        binding.healthHeaderContainer.animate().alpha(1f).setDuration(1000)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    binding.healthBarTitle.animate().alpha(1f).setDuration(1000)
                        .setListener(object : AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator?) {
                                binding.healthBarGraph.animate().alpha(1f).setDuration(1000)
                                    .setListener(object : AnimatorListenerAdapter() {
                                        override fun onAnimationEnd(animation: Animator?) {
                                            binding.healthLineTitle.animate().alpha(1f).setDuration(1000)
                                                .setListener(object : AnimatorListenerAdapter() {
                                                    override fun onAnimationEnd(animation: Animator?) {
                                                        binding.healthLineGraph.animate().alpha(1f).duration = 1000
                                                    }
                                                })
                                        }
                                    })
                            }
                        })
                }
            })

        Timer(true).schedule(object : TimerTask() {
            override fun run() {
                activity?.runOnUiThread {
                    binding.healthReportTitle.animate().alpha(1f).setDuration(1000)
                        .setListener(object : AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator?) {
                                binding.healthReportSubtitle.animate().alpha(1f).setDuration(1000)
                                    .setListener(object : AnimatorListenerAdapter() {
                                        override fun onAnimationEnd(animation: Animator?) {
                                            binding.healthReportRow1.animate().alpha(1f).setDuration(1000)
                                                .setListener(object : AnimatorListenerAdapter() {
                                                    override fun onAnimationEnd(animation: Animator?) {
                                                        binding.healthReportRow2.animate().alpha(1f).setDuration(1000)
                                                            .setListener(object : AnimatorListenerAdapter() {
                                                                override fun onAnimationEnd(animation: Animator?) {
                                                                    binding.healthReportRow3.animate()
                                                                        .alpha(1f).duration = 1000
                                                                }
                                                            })
                                                    }
                                                })
                                        }
                                    })
                            }
                        })
                }
            }
        }, 5400)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}