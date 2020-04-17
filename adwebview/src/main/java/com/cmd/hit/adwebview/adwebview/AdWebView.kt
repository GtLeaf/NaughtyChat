package com.cmd.hit.adwebview.adwebview

import android.content.Context
import android.util.AttributeSet
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient

class AdWebView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : WebView(context, attrs), IAdWebView {

    var scrollListener : IWebOnScrollListener? = null

    init {
        webChromeClient = AdWebChromeClient()
        webViewClient = AdWebViewClient()
    }

    fun setOnScrollListener(listener: IWebOnScrollListener?) {
        scrollListener = listener
    }

    override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
        super.onScrollChanged(l, t, oldl, oldt)
        scrollListener?.onScrollChanged(this, l, t, oldl, oldt)
    }

    fun setAdWebChromeClient(client: AdWebChromeClient?) {
        super.setWebChromeClient(client)
    }

    fun setAdWebViewClient(client: AdWebViewClient?) {
        super.setWebViewClient(client)
    }

}