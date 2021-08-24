package com.unidy2002.douyin.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.unidy2002.douyin.R
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
        binding.videoSlideRecyclerView.run {
            layoutManager = LinearLayoutManager(context)
            adapter = VideoSlideAdapter(
                listOf(
                    "https://vfx.mtime.cn/Video/2021/07/30/mp4/210730103510484128.mp4",
                    "https://vfx.mtime.cn/Video/2021/08/04/mp4/210804172404829125.mp4",
                    "https://vfx.mtime.cn/Video/2021/08/19/mp4/210819113439633160.mp4",
                    "https://vfx.mtime.cn/Video/2021/08/11/mp4/210811091857592154.mp4",
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
                                    findViewByPosition(currPos)
                                        ?.findViewById<JzvdHomePlayer>(R.id.video_slide_player)
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
        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val videoPlayer: JzvdHomePlayer = view.findViewById(R.id.video_slide_player)
        }

        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) =
            ViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.item_video_slide, viewGroup, false))

        override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
            viewHolder.videoPlayer.run {
                setUp(data[position], "")
                startVideo()
            }
        }

        override fun getItemCount() = data.size
    }
}