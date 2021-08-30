package com.unidy2002.douyin.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import cn.jzvd.Jzvd
import com.unidy2002.douyin.LiveActivity
import com.unidy2002.douyin.R
import com.unidy2002.douyin.SearchActivity
import com.unidy2002.douyin.components.JzvdAppPlayer
import com.unidy2002.douyin.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.homeLiveButton.setOnClickListener {
            startActivity(Intent(activity, LiveActivity::class.java))
        }

        binding.homeSearchButton.setOnClickListener {
            startActivity(Intent(activity, SearchActivity::class.java))
        }

        binding.videoSlideRecyclerView.run {
            layoutManager = LinearLayoutManager(context)
            adapter = VideoSlideAdapter(
                listOf(
                    "https://www.vertiscreen.com/wp-content/uploads/2019/10/hua-ge-shi-guo-ju-mxdia720v.mp4",
                    "https://www.vertiscreen.com/wp-content/uploads/2019/10/zha-zhi-bei-mxdia720v.mp4",
                    "https://www.vertiscreen.com/wp-content/uploads/2019/10/shi-dan-e-rong-bei-mxdia720v.mp4",
                    "https://www.vertiscreen.com/wp-content/uploads/2019/10/lyu-shi-zhe-mxdia720v.mp4",
                    "https://www.vertiscreen.com/wp-content/uploads/2021/04/fu-chou-zhe-lian-meng-mxdia720v.mp4",
                    "https://www.vertiscreen.com/wp-content/uploads/2019/10/fen-zi-liao-li-mxdia720v.mp4",
                    "https://www.vertiscreen.com/wp-content/uploads/2019/10/bai-cao-wei-li-he-mxdia720v.mp4",
                    "https://www.vertiscreen.com/wp-content/uploads/2019/10/mac-kou-hong-mxdia720v.mp4",
                    "https://www.vertiscreen.com/wp-content/uploads/2019/10/zhen-zhu-mei-xue-cai-zhuang-mxdia720v.mp4",
                )
            )

            var position = 0

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        (recyclerView.layoutManager as? LinearLayoutManager)
                            ?.run {
                                val currPos = findFirstCompletelyVisibleItemPosition()
                                if (currPos != -1 && currPos != position) {
                                    Jzvd.releaseAllVideos()
                                    findViewByPosition(currPos)
                                        ?.findViewById<JzvdAppPlayer>(R.id.video_slide_player)
                                        ?.startVideo()
                                    position = currPos
                                }
                            }
                    }
                }
            })
        }
        PagerSnapHelper().attachToRecyclerView(binding.videoSlideRecyclerView)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    inner class VideoSlideAdapter(private val data: List<String>) :
        RecyclerView.Adapter<VideoSlideAdapter.ViewHolder>() {

        private var zeroInitialized = false

        private val defaultText = "@人民日报\n奥运版《错位时空》，看到泪目！多想你能看到，今天奥运赛场上的中国骄傲！"

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val videoPlayer: JzvdAppPlayer? = view.findViewById(R.id.video_slide_player)
            val text: TextView? = view.findViewById(R.id.video_text)
            val heart: ImageView? = view.findViewById(R.id.video_heart_image)
            val restButton: TextView? = view.findViewById(R.id.video_no_more_rest_button)
        }

        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) =
            ViewHolder(
                LayoutInflater.from(viewGroup.context).inflate(
                    if (viewType == 0) R.layout.item_video_slide else R.layout.item_video_no_more, viewGroup, false
                )
            )

        override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
            viewHolder.videoPlayer?.run {
                setUp(data[position], "")
                if (position == 0 && !zeroInitialized) {
                    zeroInitialized = true
                    startVideo()
                }
            }
            viewHolder.text?.text = defaultText
            viewHolder.restButton?.setOnClickListener {
                activity?.finish()
            }
            viewHolder.heart?.run {
                var selected = false
                setOnClickListener {
                    selected = !selected
                    setImageResource(if (selected) R.drawable.red_heart else R.drawable.video_like_icon)
                }
            }
        }

        override fun getItemCount() = data.size + 1

        override fun getItemViewType(position: Int) = if (position == data.size) 1 else 0
    }
}