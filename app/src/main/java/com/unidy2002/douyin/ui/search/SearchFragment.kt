package com.unidy2002.douyin.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.unidy2002.douyin.R
import com.unidy2002.douyin.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)

        binding.searchBackImage.setOnClickListener {
            activity?.finish()
        }

        binding.searchHotRecyclerView.run {
            adapter = SearchHotAdapter(
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
                )
            )
            layoutManager = LinearLayoutManager(context)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    inner class SearchHotAdapter(private val data: List<String>) :
        RecyclerView.Adapter<SearchHotAdapter.ViewHolder>() {
        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val id: TextView? = view.findViewById(R.id.search_hot_id)
            val content: TextView? = view.findViewById(R.id.search_hot_content)
        }

        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) =
            ViewHolder(
                LayoutInflater.from(viewGroup.context).inflate(
                    if (viewType == 0) R.layout.item_search_header else R.layout.item_search_hot, viewGroup, false
                )
            )

        override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
            if (position == 0) return
            viewHolder.run {
                when (position) {
                    1 -> {
                        id?.setTextColor(resources.getColor(R.color.hot_1_fore, null))
                        id?.background?.setTint(resources.getColor(R.color.hot_1_back, null))
                    }
                    2 -> {
                        id?.setTextColor(resources.getColor(R.color.hot_2_fore, null))
                        id?.background?.setTint(resources.getColor(R.color.hot_2_back, null))
                    }
                    3 -> {
                        id?.setTextColor(resources.getColor(R.color.hot_3_fore, null))
                        id?.background?.setTint(resources.getColor(R.color.hot_3_back, null))
                    }
                    else -> {
                        id?.setTextColor(resources.getColor(R.color.grey, null))
                        id?.background?.setTint(0)
                    }
                }
                id?.text = position.toString()
                content?.text = data[position - 1]
            }
        }

        override fun getItemCount() = data.size

        override fun getItemViewType(position: Int) = if (position == 0) 0 else 1
    }
}