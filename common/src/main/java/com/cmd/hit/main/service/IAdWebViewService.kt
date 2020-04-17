package com.cmd.hit.main.service

import android.webkit.WebView

interface IAdWebViewService : IService {
    fun obtainAdWebView(): WebView?
}