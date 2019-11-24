package com.cmd.hit.adwebview

import android.annotation.TargetApi
import android.graphics.Bitmap
import android.net.Uri
import android.net.http.SslError
import android.os.Build
import android.util.Log
import android.webkit.*
import com.cmd.hit.adwebview.jsBridge.JsBridgeHandler

class AdWebViewClient : WebViewClient() {

    var jsBridgeHandler: JsBridgeHandler? = null
    val TAG = "AdWebViewClient"

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        Log.d(TAG, "shouldOverrideUrlLoading")
        request?.url?.let {
            return checkScheme(it)
        }
        return true
    }

    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
        Log.d(TAG, "shouldOverrideUrlLoading")
        url?.let {
            return checkScheme(Uri.parse(url))
        }
        return super.shouldOverrideUrlLoading(view, url)
    }

    //TODO 需调整返回值
    private fun checkScheme(uri: Uri): Boolean {
        if (uri.scheme == AdWebViewConstants.AD_WEBVIEW_SCHEME) {
            if (uri.host == AdWebViewConstants.AD_WEBVIEW_HOST) {
                //去请求消息队列
            } else {
                //去调用单个方法
                jsBridgeHandler?.invokeAction(uri.host?: "", "1")
            }
        }
        return true
    }

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        Log.d(TAG, "onPageStarted")
        super.onPageStarted(view, url, favicon)
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        Log.d(TAG, "onPageFinished")
        super.onPageFinished(view, url)
    }

    override fun onReceivedError(
        view: WebView?,
        request: WebResourceRequest?,
        error: WebResourceError?
    ) {
        Log.d(TAG, "onReceivedError")
        super.onReceivedError(view, request, error)
    }

    override fun onReceivedHttpError(
        view: WebView?,
        request: WebResourceRequest?,
        errorResponse: WebResourceResponse?
    ) {
        Log.d(TAG, "onReceivedHttpError")
        super.onReceivedHttpError(view, request, errorResponse)
    }


    override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
        Log.d(TAG, "onReceivedSslError")
        super.onReceivedSslError(view, handler, error)
    }
}