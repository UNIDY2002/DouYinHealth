package com.unidy2002.douyin.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.unidy2002.douyin.MainActivity
import com.unidy2002.douyin.R
import com.unidy2002.douyin.databinding.FragmentConfigurationBinding
import com.unidy2002.douyin.models.currentUser

class ConfigurationFragment : Fragment() {

    private var _binding: FragmentConfigurationBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConfigurationBinding.inflate(inflater, container, false)
        binding.configurationTimeText.setOnClickListener {
            binding.configurationTimeText.setTextColor(resources.getColor(R.color.grey, null))
            binding.configurationTimeSplit.setBackgroundColor(resources.getColor(R.color.grey, null))
            binding.configurationFraudText.setTextColor(resources.getColor(R.color.light_grey, null))
            binding.configurationFraudSplit.setBackgroundColor(resources.getColor(R.color.light_grey, null))
            binding.configurationTimeGroup.visibility = View.VISIBLE
            binding.configurationFraudGroup.visibility = View.GONE
        }

        binding.configurationFraudText.setOnClickListener {
            binding.configurationFraudText.setTextColor(resources.getColor(R.color.grey, null))
            binding.configurationFraudSplit.setBackgroundColor(resources.getColor(R.color.grey, null))
            binding.configurationTimeText.setTextColor(resources.getColor(R.color.light_grey, null))
            binding.configurationTimeSplit.setBackgroundColor(resources.getColor(R.color.light_grey, null))
            binding.configurationFraudGroup.visibility = View.VISIBLE
            binding.configurationTimeGroup.visibility = View.GONE
        }

        binding.configurationActionButton.setOnClickListener {
            currentUser = "visitor"
            startActivity(Intent(activity, MainActivity::class.java))
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}