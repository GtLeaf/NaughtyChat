package com.cmd.hit.main.service.emptyService

import android.webkit.WebView
import com.cmd.hit.main.service.IAdWebViewService

class EmptyAdWebViewService : IAdWebViewService {
    override fun obtainAdWebView(): WebView? {
        return null
    }
}