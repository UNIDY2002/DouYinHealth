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
    }

}