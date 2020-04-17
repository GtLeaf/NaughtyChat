package com.cmd.hit.main.view.adlightwebpage

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.webkit.*
import android.widget.FrameLayout
import com.cmd.hit.adwebview.adwebview.IWebOnScrollListener
import com.cmd.hit.main.R
import kotlinx.android.synthetic.main.main_light_web_page_view.view.*

class AdLightWebPageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    var webUrl = "https://ultimavip.cn/m/kingApply.html?source=jrtttzL014card1_2"
//    var webUrl = "http://blog.csdn.net/qq_29269233"

    private val titleBarListener = object : AdWebPageTitleBar.TitleBarListener {
        override fun onClickCloseAllPage() {
            dismiss()
        }

        override fun onClickGoBack() {
        }

        override fun onClickReport() {
        }

        override fun onClickShare() {
        }

        override fun onClickMenu(view: View) {
        }
    }

    init {
        View.inflate(context, R.layout.main_light_web_page_view, this)
        bottom_sheet_container.bindBehavior(fl_web_page)
        initWebView()
        initListener()
    }

    private fun initWebView() {
        //支持javascript
        ad_webview.settings.javaScriptEnabled = true
        // 设置可以支持缩放
        ad_webview.settings.setSupportZoom(true)
        // 设置出现缩放工具
        ad_webview.settings.builtInZoomControls = true
        //扩大比例的缩放
        ad_webview.settings.useWideViewPort = true
        //自适应屏幕
        ad_webview.settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
        ad_webview.settings.loadWithOverviewMode = true

        ad_webview.setOnScrollListener(object : IWebOnScrollListener {
            override fun onScrollChanged(v: View, l: Int, t: Int, oldl: Int, oldt: Int) {
                bottom_sheet_container.collapsible = (v.scrollY <= 0)
            }
        })

    }

    private fun initListener() {
        web_page_title_bar.titleBarListener = titleBarListener
    }

    fun show() {
        bottom_sheet_container.collapse()
        fl_webview_container.visibility = View.VISIBLE
        ad_webview.loadUrl(webUrl)
    }

    fun dismiss() {
        bottom_sheet_container.hide()
    }
}