package com.unidy2002.douyin.components

import android.content.Context
import android.util.AttributeSet
import cn.jzvd.JzvdStd

class JzvdAppPlayer(context: Context, attrs: AttributeSet) : JzvdStd(context, attrs) {
    override fun setAllControlsVisiblity(
        topCon: Int,
        bottomCon: Int,
        startBtn: Int,
        loadingPro: Int,
        posterImg: Int,
        bottomPro: Int,
        retryLayout: Int
    ) {
        topContainer.visibility = INVISIBLE
        bottomContainer.visibility = INVISIBLE
        startButton.visibility = INVISIBLE
        loadingProgressBar.visibility = INVISIBLE
        posterImageView.visibility = INVISIBLE
        bottomProgressBar.visibility = INVISIBLE
        mRetryLayout.visibility = INVISIBLE
    }

    override fun onCompletion() {
        super.onCompletion()
        startVideo()
    }
}