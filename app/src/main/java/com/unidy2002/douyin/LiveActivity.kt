package com.unidy2002.douyin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import cn.jzvd.Jzvd
import com.unidy2002.douyin.components.JzvdAppPlayer
import com.unidy2002.douyin.databinding.ActivityLiveBinding

class LiveActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLiveBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLiveBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.liveSlideRecyclerView.run {
            layoutManager = LinearLayoutManager(context)
            adapter = LiveSlideAdapter(
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
        PagerSnapHelper().attachToRecyclerView(binding.liveSlideRecyclerView)
    }

    inner class LiveSlideAdapter(private val data: List<String>) :
        RecyclerView.Adapter<LiveSlideAdapter.ViewHolder>() {

        private var zeroInitialized = false

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val videoPlayer: JzvdAppPlayer = view.findViewById(R.id.live_slide_player)
            val moreLiveImage: ImageView = view.findViewById(R.id.live_more_live_image)
        }

        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) =
            ViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.item_live_slide, viewGroup, false))

        override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
            viewHolder.videoPlayer.run {
                setUp(data[position], "")
                if (position == 0 && !zeroInitialized) {
                    zeroInitialized = true
                    startVideo()
                }
            }
            viewHolder.moreLiveImage.setOnClickListener {
                binding.root.openDrawer(Gravity.END)
            }
        }

        override fun getItemCount() = data.size
    }
}