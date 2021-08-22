package com.unidy2002.douyin.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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
            adapter = VideoSlideAdapter(listOf("aaa", "bbb", "ccc"))
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
            val textView: TextView = view.findViewById(R.id.video_slide_text)
        }

        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) =
            ViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.item_video_slide, viewGroup, false))

        override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
            viewHolder.textView.text = data[position]
        }

        override fun getItemCount() = data.size
    }
}