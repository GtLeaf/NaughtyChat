package com.cmd.hit.main.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.webkit.WebSettings
import android.widget.FrameLayout
import com.cmd.hit.main.R
import com.cmd.hit.main.utils.ScreenUtil
import kotlinx.android.synthetic.main.main_ad_float_web_page.view.*

class AdFloatWebPageView @JvmOverloads constructor(
    context: Context,
    attr: AttributeSet? = null,
    defStyle: Int = 0
): FrameLayout(context, attr, defStyle) {

    var webUrl = "https://ultimavip.cn/m/kingApply.html?source=jrtttzL014card1_2"

    private val toTransY: Float
        get() {
            val loc = IntArray(2)
            this.getLocationOnScreen(loc)
            return (ScreenUtil.getScreenHeight(context) - loc[1]).toFloat()
        }

    init {
        View.inflate(context, R.layout.main_ad_float_web_page, this)
        initWebView()
        initListener()
    }

    private fun initListener() {
        iv_float_page_close.setOnClickListener {
            translationOutScreen()
        }
    }

    private fun initWebView(){
        float_page_adwebview.apply {
            //支持javascript
            settings.javaScriptEnabled = true
            // 设置可以支持缩放
            settings.setSupportZoom(true)
            // 设置出现缩放工具
            settings.builtInZoomControls = true
            //扩大比例的缩放
            settings.useWideViewPort = true
            //自适应屏幕
            settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
            settings.loadWithOverviewMode = true
            //不展示滚动条
            isHorizontalScrollBarEnabled = false
            isVerticalScrollBarEnabled = false
        }
    }

    fun loadUrl(url: String? = null) {
        webUrl = if (url.isNullOrEmpty()) {
            webUrl
        } else {
            url
        }
        float_page_adwebview.loadUrl(url?: webUrl)
    }

    fun moveOutFromScreen() {
        fl_web_container.translationY = toTransY
        v_float_page_mask.visibility = GONE
        v_float_page_mask.alpha = 0f
    }

    fun translationInScreen() {
        v_float_page_mask.alpha = 0f
        v_float_page_mask.visibility = VISIBLE
        v_float_page_mask.animate()
            .setDuration(200L)
            .alpha(1f)
            .start()

        fl_web_container.animate()
            .setDuration(200L)
            .translationY(0f)
            .start()
    }

    fun translationOutScreen() {
        v_float_page_mask.animate()
            .setDuration(200L)
            .alpha(0f)
            .withEndAction { v_float_page_mask.visibility = View.GONE }
            .start()

        fl_web_container.animate()
            .setDuration(200L)
            .translationY(toTransY)
            .start()
    }
}