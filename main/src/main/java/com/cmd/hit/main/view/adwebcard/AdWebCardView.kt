package com.cmd.hit.main.view.adwebcard

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import com.cmd.hit.main.R
import com.cmd.hit.main.utils.ScreenUtil
import kotlinx.android.synthetic.main.main_ad_web_card_view.view.*

class AdWebCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr)  {

    var callback: Callback? = null

    init {
        View.inflate(context, R.layout.main_ad_web_card_view, this)
        //初始化view的各种属性
        val params = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        params.gravity = Gravity.BOTTOM
        params.setMargins(dip2Px(MARGIN_LEFT).toInt(), 0, 0, dip2Px(MARGIN_BOTTOM).toInt())
        layoutParams = params

        initListener()
    }

    private fun initListener() {
        iv_card_close.setOnClickListener { callback?.onCloseClick() }
    }


    fun dip2Px (dpValue: Float): Float {
        return ScreenUtil.dip2Px(context, dpValue)
    }

    fun showCardView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            z = 0f
            visibility = View.VISIBLE
        } else {
            visibility = View.VISIBLE
        }
    }

    fun hideCardView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // 将卡片z-order调低，再将其属性提前设置为可见状态
            z = -1f
            visibility = View.VISIBLE
        } else {
            visibility = View.INVISIBLE
        }
    }

    interface Callback {
        fun onCloseClick()
    }

    companion object {
        const val MARGIN_BOTTOM = 8f
        const val MARGIN_LEFT = 10f
    }
}