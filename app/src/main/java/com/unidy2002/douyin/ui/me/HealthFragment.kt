package com.unidy2002.douyin.ui.me

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.*
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

        binding.healthDailyChart.run {
            data = BarData(
                BarDataSet(
                    listOf(
                        BarEntry(0f, floatArrayOf(0f, 0f)),
                        BarEntry(1f, floatArrayOf(0f, 0f)),
                        BarEntry(2f, floatArrayOf(8f, 0f)),
                        BarEntry(3f, floatArrayOf(15f, 0f)),
                        BarEntry(4f, floatArrayOf(0f, 10f)),
                        BarEntry(5f, floatArrayOf(21f, 30f)),
                        BarEntry(6f, floatArrayOf(13f, 60f)),
                        BarEntry(7f, floatArrayOf(55f, 5f)),
                        BarEntry(8f, floatArrayOf(24f, 0f)),
                        BarEntry(9f, floatArrayOf(40f, 0f)),
                        BarEntry(10f, floatArrayOf(0f, 120f)),
                        BarEntry(11f, floatArrayOf(0f, 12f)),
                    ), "Label"
                )
            ).apply {
                barWidth = 0.5f
            }
            setFitBars(true)
            invalidate()
        }

        binding.healthMonthlyChart.run {
            data = LineData(
                LineDataSet(
                    listOf(
                        Entry(3f, 135f),
                        Entry(4f, 189f),
                        Entry(5f, 97f),
                        Entry(6f, 117f),
                        Entry(7f, 131f),
                        Entry(8f, 106f),
                    ), getString(R.string.watch_video)
                ).apply {
                    axisDependency = YAxis.AxisDependency.LEFT
                },
                LineDataSet(
                    listOf(
                        Entry(3f, 30f),
                        Entry(4f, 58f),
                        Entry(5f, 43f),
                        Entry(6f, 60f),
                        Entry(7f, 90f),
                        Entry(8f, 26f),
                    ), getString(R.string.watch_live)
                ).apply {
                    axisDependency = YAxis.AxisDependency.LEFT
                },
            )
            invalidate()
        }

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