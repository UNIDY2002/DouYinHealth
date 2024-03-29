package com.unidy2002.douyin.ui.search

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.unidy2002.douyin.R
import com.unidy2002.douyin.databinding.FragmentSearchBinding
import java.util.*

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    @SuppressLint("InflateParams", "ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)

        binding.searchBackImage.setOnClickListener {
            activity?.finish()
        }

        listOf(
            "张惠妹第一条抖音",
            "坑田姥姥的外孙女上线了",
            "住四平方的复式楼是什么体验",
            "逗猫界的天花板",
            "霍华德360度暴扣",
            "开学自我介绍",
            "女生肺癌晚期拍短视频微家人留念",
            "光之变装",
            "喀布尔机场爆炸已造成170人死亡",
            "网信办要求取消明星艺人榜",
            "苏炳添心中的苏神",
            "赵薇被多部影视剧除名",
            "恐袭后美国能如期撤军吗",
            "当你问东风快递能否送到",
            "郑爽道歉",
        ).forEachIndexed { index, content ->
            binding.searchHotLinearLayout.addView(
                layoutInflater.inflate(R.layout.item_search_hot, null).apply {
                    val idText: TextView = findViewById(R.id.search_hot_id)
                    val contentText: TextView = findViewById(R.id.search_hot_content)
                    idText.text = (index + 1).toString()
                    contentText.text = content
                    when (index) {
                        0 -> {
                            idText.setTextColor(resources.getColor(R.color.hot_1_fore, null))
                            idText.background.setTint(resources.getColor(R.color.hot_1_back, null))
                        }
                        1 -> {
                            idText.setTextColor(resources.getColor(R.color.hot_2_fore, null))
                            idText.background.setTint(resources.getColor(R.color.hot_2_back, null))
                        }
                        2 -> {
                            idText.setTextColor(resources.getColor(R.color.hot_3_fore, null))
                            idText.background.setTint(resources.getColor(R.color.hot_3_back, null))
                        }
                        else -> {
                            idText.setTextColor(resources.getColor(R.color.grey, null))
                            idText.background.setTint(0)
                        }
                    }
                }
            )
        }

        var voiceTouched = false

        binding.searchVoiceButton.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN && !voiceTouched) {
                voiceTouched = true
                Timer(true).schedule(object : TimerTask() {
                    override fun run() {
                        activity?.runOnUiThread {
                            binding.searchEditText.setText("养")
                            binding.searchEditText.setSelection(1)
                        }
                        Timer(true).schedule(object : TimerTask() {
                            override fun run() {
                                activity?.runOnUiThread {
                                    binding.searchEditText.setText("养生")
                                    binding.searchEditText.setSelection(2)
                                }
                            }
                        }, 300)
                    }

                }, 2500)
            }
            binding.searchVoiceButton.setImageResource(if (event.action == MotionEvent.ACTION_DOWN) R.drawable.voice_image_grey else R.drawable.voice_image)
            true
        }
        binding.searchVoiceButton.setOnClickListener { }
        binding.searchActionButton.setOnClickListener { }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}