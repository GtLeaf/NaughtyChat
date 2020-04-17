package com.cmd.hit.main.view.adlightwebpage

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.webkit.WebSettings
import android.widget.FrameLayout
import com.cmd.hit.adwebview.adwebview.AdWebView

/**
 * 目前LoadUrl还不能正常展示
 */
class AdWebViewContainer @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    val webView = AdWebView(context).apply {
        layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
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
    }

    init {
        this.addView(webView)
    }

    fun loadUrl(url: String) {
        webView.loadUrl(url)
    }
}