package com.unidy2002.douyin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.fragment.NavHostFragment
import com.unidy2002.douyin.databinding.ActivityMainBinding
import com.unidy2002.douyin.models.currentUser

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var focus = 0

    private val targets
        get() = listOf(
            binding.bottomNavItemHome,
            binding.bottomNavItemMe,
        )

    private fun changeFocus(target: Int) {
        targets[focus].background = null
        targets[target].background =
            ResourcesCompat.getDrawable(resources, R.drawable.bottom_nav_selected_background, null)
        focus = target
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        (supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as? NavHostFragment)?.run {
            binding.bottomNavItemHome.setOnClickListener {
                changeFocus(0)
                navController.navigate(R.id.navigation_home)
            }

            binding.bottomNavItemMe.setOnClickListener {
                if (currentUser == null) {
                    startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                } else {
                    changeFocus(1)
                    navController.navigate(R.id.navigation_me)
                }
            }
        }
    }
}