package com.unidy2002.douyin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.unidy2002.douyin.databinding.ActivityMainBinding
import com.unidy2002.douyin.models.currentUser

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        (supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as? NavHostFragment)?.run {
            NavigationUI.setupWithNavController(binding.navView, navController)

            binding.navView.setOnItemSelectedListener {
                if (it.itemId != R.id.navigation_home && currentUser == null) {
                    startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                    false
                } else {
                    navController.navigate(it.itemId)
                    true
                }
            }
        }
    }
}