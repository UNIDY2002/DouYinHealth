package com.unidy2002.douyin.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.TextView
import androidx.annotation.MenuRes
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import com.unidy2002.douyin.R
import com.unidy2002.douyin.databinding.FragmentConfigurationBinding
import com.unidy2002.douyin.models.currentUser

class ConfigurationFragment : Fragment() {

    private var _binding: FragmentConfigurationBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var currentFocus = 0  // 0: none; 1: time; 2: fraud

    private val donateValidRange = 0..999

    private fun toggle(open: Boolean, text: TextView, split: View, group: View) {
        text.setTextColor(resources.getColor(if (open) R.color.grey else R.color.light_grey, null))
        split.setBackgroundColor(resources.getColor(if (open) R.color.grey else R.color.light_grey, null))
        group.visibility = if (open) View.VISIBLE else View.GONE
    }

    private fun toggleTime(open: Boolean) {
        toggle(open, binding.configurationTimeText, binding.configurationTimeSplit, binding.configurationTimeGroup)
    }

    private fun toggleFraud(open: Boolean) {
        toggle(open, binding.configurationFraudText, binding.configurationFraudSplit, binding.configurationFraudGroup)
    }

    private fun bindTextToPopupMenu(textView: TextView, @MenuRes menuRes: Int) {
        textView.setOnClickListener {
            PopupMenu(context, textView).run {
                menuInflater.inflate(menuRes, menu)
                setOnMenuItemClickListener {
                    textView.text = it.title
                    true
                }
                show()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConfigurationBinding.inflate(inflater, container, false)
        binding.configurationTimeText.setOnClickListener {
            toggleTime(currentFocus != 1)
            toggleFraud(false)
            currentFocus = if (currentFocus == 0) 1 else 0
        }

        binding.configurationFraudText.setOnClickListener {
            toggleTime(false)
            toggleFraud(currentFocus != 2)
            currentFocus = if (currentFocus == 0) 2 else 0
        }

        bindTextToPopupMenu(binding.configureVideoSingleMaxText, R.menu.video_single_max_menu)

        bindTextToPopupMenu(binding.configureVideoTotalMaxText, R.menu.video_total_max_menu)

        bindTextToPopupMenu(binding.configureVideoRestTimeText, R.menu.rest_time_menu)

        bindTextToPopupMenu(binding.configureVideoTotalLiveText, R.menu.live_total_max_menu)

        binding.configureVideoDonateMaxEditText.doAfterTextChanged {
            it?.toString()?.toIntOrNull()?.run {
                val valid = this in donateValidRange
                binding.configureVideoDonateMaxEditText.setTextColor(
                    resources.getColor(if (valid) R.color.black else R.color.red, null)
                )
                binding.configurationActionButton.isEnabled = valid
            }
        }

        binding.configureVideoBlockSalesSwitch.setOnCheckedChangeListener { _, isChecked ->
            binding.configureVideoBlockSalesResult.setText(if (isChecked) R.string.yes else R.string.no)
        }

        binding.configurationActionButton.setOnClickListener {
            currentUser = "visitor"
            activity?.finish()
        }


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}