package com.unidy2002.douyin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.unidy2002.douyin.databinding.ActivityHealthBinding

class HealthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHealthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHealthBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}