package com.unidy2002.douyin.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.DrawableRes
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
import java.util.*

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
                    VideoInfo(
                        "https://www.vertiscreen.com/wp-content/uploads/2019/10/hua-ge-shi-guo-ju-mxdia720v.mp4",
                        "@华格仕\n惬意生活，从品尝美食开始。好锅、好火、好食材，才能做出可口佳肴。",
                        R.drawable.home_avatar_0,
                    ),
                    VideoInfo(
                        "https://www.vertiscreen.com/wp-content/uploads/2019/10/zha-zhi-bei-mxdia720v.mp4",
                        "@dimo\n水杯？榨汁机？沉浸式吃水果 #生活#",
                        R.drawable.home_avatar_1,
                    ),
                    VideoInfo(
                        "https://www.vertiscreen.com/wp-content/uploads/2019/10/shi-dan-e-rong-bei-mxdia720v.mp4",
                        "@SIDANDA\n轻盈如雪 #视觉享受#",
                        R.drawable.home_avatar_2,
                    ),
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
                                    (findViewHolderForAdapterPosition(currPos) as? VideoSlideAdapter.ViewHolder)?.run {
                                        videoPlayer?.startVideo()
                                        if (currPos == 1) {
                                            Timer(true).schedule(object : TimerTask() {
                                                override fun run() {
                                                    activity?.runOnUiThread {
                                                        pop(View.VISIBLE, 2)
                                                    }
                                                }
                                            }, 5000)
                                        }
                                    }
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

    inner class VideoSlideAdapter(private val data: List<VideoInfo>) :
        RecyclerView.Adapter<VideoSlideAdapter.ViewHolder>() {

        private var zeroInitialized = false

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val videoPlayer: JzvdAppPlayer? = view.findViewById(R.id.video_slide_player)
            val text: TextView? = view.findViewById(R.id.video_text)
            val heart: ImageView? = view.findViewById(R.id.video_heart_image)
            val avatar: ImageView? = view.findViewById(R.id.video_follow_image)
            val restButton: TextView? = view.findViewById(R.id.video_no_more_rest_button)
            private val blurView: View? = view.findViewById(R.id.video_reminder_blur_view)
            private val reminderImage: ImageView? = view.findViewById(R.id.video_time_reminder_image)
            private val popupText0: TextView? = view.findViewById(R.id.popup_text_0)
            private val popupText1: TextView? = view.findViewById(R.id.popup_text_1)
            val reminderRestButton: TextView? = view.findViewById(R.id.video_reminder_rest_button)
            val iWantMore: TextView? = view.findViewById(R.id.video_reminder_i_want_more_button)
            val adBlockedText: LinearLayout? = view.findViewById(R.id.home_ad_blocked_text)

            fun pop(visibility: Int, useText: Int) {
                blurView?.visibility = visibility
                reminderImage?.visibility = visibility
                popupText0?.visibility = visibility
                popupText1?.visibility = visibility
                reminderRestButton?.visibility = visibility
                iWantMore?.visibility = visibility
                if (visibility == View.VISIBLE) {
                    popupText0?.setText(if (useText == 1) R.string.reminder_title_1 else R.string.reminder_title_2)
                    popupText1?.setText(if (useText == 1) R.string.reminder_subtitle_1 else R.string.reminder_subtitle_2)
                    videoPlayer?.mediaInterface?.pause()
                } else {
                    videoPlayer?.mediaInterface?.start()
                }
            }
        }

        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) =
            ViewHolder(
                LayoutInflater.from(viewGroup.context).inflate(
                    if (viewType == 0) R.layout.item_video_slide else R.layout.item_video_no_more, viewGroup, false
                )
            )

        override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
            viewHolder.videoPlayer?.run {
                setUp(data[position].url, "")
                if (position == 0 && !zeroInitialized) {
                    zeroInitialized = true
                    startVideo()
                    Timer(true).schedule(object : TimerTask() {
                        override fun run() {
                            activity?.runOnUiThread {
                                viewHolder.pop(View.VISIBLE, 1)
                            }
                        }
                    }, 12000)
                    Timer(true).schedule(object : TimerTask() {
                        override fun run() {
                            activity?.runOnUiThread {
                                viewHolder.adBlockedText?.animate()?.translationX(-2000f)?.duration = 9000
                            }
                        }
                    }, 2000)
                }
            }
            viewHolder.text?.text = data[position].description
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
            viewHolder.avatar?.setImageResource(data[position].avatar)
            viewHolder.reminderRestButton?.setOnClickListener {
                activity?.finish()
            }
            viewHolder.iWantMore?.setOnClickListener {
                viewHolder.pop(View.GONE, 0)
            }
        }

        override fun getItemCount() = data.size + 1

        override fun getItemViewType(position: Int) = if (position == data.size) 1 else 0
    }

    data class VideoInfo(
        val url: String,
        val description: String,
        @DrawableRes val avatar: Int,
    )
}