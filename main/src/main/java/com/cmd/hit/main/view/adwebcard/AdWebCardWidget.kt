package com.cmd.hit.main.view.adwebcard

import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.ViewGroup
import com.cmd.hit.main.base.EventData
import com.cmd.hit.main.utils.ScreenUtil
import com.cmd.hit.main.view.AdWidgetConstants.ACTION_WEB_CARD_SHOW
import com.cmd.hit.main.widget.AbsAdWidget

class AdWebCardWidget : AbsAdWidget() {

    var adWebCardView : AdWebCardView? = null

    private val toTransX: Float
        get() {
            adWebCardView?.let {
                return -(it.width + (it.layoutParams as ViewGroup.MarginLayoutParams).rightMargin).toFloat()
            }
            return 0f
        }

    override fun observe() {
        super.observe()
        mEventCenter?.observe(ACTION_WEB_CARD_SHOW, this)
    }


    override fun onChanged(t: EventData?) {
        super.onChanged(t)
        t?: return
        when (t.key) {
            ACTION_WEB_CARD_SHOW -> {
                show()
            }
        }
    }
    override fun onBindView(view: View?) {
        super.onBindView(view)
        val context = mContext?: return

        if(adWebCardView == null) {
            adWebCardView = AdWebCardView(context)
            (mContentView as ViewGroup).addView(adWebCardView)
        }

        adWebCardView?.apply {
            setBackgroundColor(Color.parseColor("#80000000"))
            alpha = 0f
            callback = webCardCallback
            hideCardView()
        }
    }

    fun show() {
        moveOutFromScreen()
        adWebCardView?.showCardView()
        translationInScreen()
    }

    fun hide() {
        translationOutScreen()
    }

    fun reset() {
        adWebCardView?.let {
            it.alpha = 0f
            it.visibility = View.GONE
            moveOutFromScreen()
        }
    }

    fun moveOutFromScreen() {
        adWebCardView?.let {
            it.translationX = toTransX
        }
    }

    fun translationInScreen() {
        adWebCardView?.let {
            it.animate()
                .setDuration(200)
                .alpha(1f)
                .translationX(0f)
                .start()
        }
    }

    fun translationOutScreen() {
        adWebCardView?.let {
            it.animate()
                .setDuration(200)
                .alpha(0f)
                .translationX(toTransX)
                .start()
        }
    }

    fun dip2Px(dpValue: Float): Float {
        return ScreenUtil.dip2Px(mContext, dpValue)
    }

    private val webCardCallback = object : AdWebCardView.Callback {
        override fun onCloseClick() {
            hide()
        }
    }

    companion object {
        //默认宽高，单位dp
        const val WIDTH = 270f
        const val HEIGHT = 147f
    }
}