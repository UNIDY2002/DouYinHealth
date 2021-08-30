package com.unidy2002.douyin.ui.health

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.unidy2002.douyin.R
import com.unidy2002.douyin.databinding.FragmentExchangeBinding

class ExchangeFragment : Fragment() {

    private var _binding: FragmentExchangeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    @SuppressLint("InflateParams")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExchangeBinding.inflate(inflater, container, false)

        binding.exchangeBack.setOnClickListener { NavHostFragment.findNavController(this).navigateUp() }

        binding.exchangeCollectAllButton.setOnClickListener { }

        binding.exchangeDailyRewards.run {
            listOf(
                RewardItem("25日", 135, 85, true, 2),
                RewardItem("26日", 165, 58, true, 2),
                RewardItem("27日", 97, 80, true, 2),
                RewardItem("28日", 117, 55, true, 2),
                RewardItem("29日", 131, 190, false, 1),
                RewardItem("30日", 106, 120, false, 2),
                RewardItem("31日", 176, 237, true, 0),
            ).forEach {
                addView(
                    layoutInflater.inflate(R.layout.item_exchange_daily_reward, null).apply {
                        findViewById<TextView>(R.id.exchange_daily_reward_date_text).text = it.date
                        findViewById<TextView>(R.id.exchange_daily_reward_dark_red).run {
                            layoutParams =
                                LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, it.dark.toFloat())
                            text = it.dark.toString()
                        }
                        findViewById<TextView>(R.id.exchange_daily_reward_light_red).run {
                            layoutParams =
                                LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, it.light.toFloat())
                            text = it.light.toString()
                        }
                        findViewById<TextView>(R.id.exchange_daily_reward_blank).layoutParams =
                            LinearLayout.LayoutParams(
                                0, LinearLayout.LayoutParams.MATCH_PARENT, (440 - it.dark - it.light).toFloat()
                            )
                        findViewById<TextView>(R.id.exchange_daily_reward_collected_status).run {
                            setText(if (it.collected) R.string.collected else R.string.uncollected)
                            isEnabled = !it.collected
                            setOnClickListener { }
                        }
                        findViewById<ImageView>(R.id.exchange_daily_reward_coin_left).visibility =
                            if (it.number == 2) View.VISIBLE else View.GONE
                        findViewById<ImageView>(R.id.exchange_daily_reward_coin_right).visibility =
                            if (it.number >= 1) View.VISIBLE else View.GONE
                    }
                )
            }
        }

        binding.exchangeShopGridLayout.run {
            listOf(
                ExchangeItem(R.drawable.coupon0, "蔬菜6折", 120, false),
                ExchangeItem(R.drawable.coupon0, "蔬菜8折", 60, true),
                ExchangeItem(R.drawable.coupon0, "水果6折", 180, false),
                ExchangeItem(R.drawable.coupon1, "水果8折", 90, true),
                ExchangeItem(R.drawable.coupon1, "肉类6折", 240, false),
                ExchangeItem(R.drawable.coupon1, "肉类6折", 120, false),
                ExchangeItem(R.drawable.coupon2, "鸡蛋6折", 100, true),
                ExchangeItem(R.drawable.coupon2, "鸡蛋8折", 50, true),
                ExchangeItem(R.drawable.coupon2, "家电8折", 800, false),
            ).forEach {
                addView(
                    layoutInflater.inflate(R.layout.item_exchange_item, null).apply {
                        findViewById<TextView>(R.id.exchange_item_discount_text).text = it.discountDescription
                        layoutParams = GridLayout.LayoutParams(
                            GridLayout.spec(GridLayout.UNDEFINED),
                            GridLayout.spec(GridLayout.UNDEFINED, GridLayout.FILL, 1f)
                        ).apply { width = 0 }
                        findViewById<TextView>(R.id.exchange_item_cost_text).text = it.cost.toString()
                        findViewById<TextView>(R.id.exchange_item_exchange_button).run {
                            setOnClickListener { }
                            isEnabled = it.enabled
                        }
                        findViewById<ImageView>(R.id.exchange_item_coupon_image).setImageResource(it.couponRes)
                    }
                )
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private data class ExchangeItem(
        @DrawableRes val couponRes: Int,
        val discountDescription: String,
        val cost: Int,
        val enabled: Boolean,
    )

    private data class RewardItem(
        val date: String,
        val dark: Int,
        val light: Int,
        val collected: Boolean,
        val number: Int,
    )
}