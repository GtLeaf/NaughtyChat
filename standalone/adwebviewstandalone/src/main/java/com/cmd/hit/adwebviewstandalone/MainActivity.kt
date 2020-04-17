package com.cmd.hit.adwebviewstandalone

import android.annotation.SuppressLint
import android.content.pm.ApplicationInfo
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.*
import com.cmd.hit.adwebview.adwebview.AdWebChromeClient
import com.cmd.hit.adwebview.adwebview.AdWebView
import com.cmd.hit.adwebview.adwebview.AdWebViewClient
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var behavior: BottomSheetBehavior<AdWebView>? = null
    private var url = "https://www.baidu.com"
        get() {
            et_url.text.toString().let {
                return if (it.isBlank()) {
                    field
                } else {
                    it
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initListener()
    }

    private fun initView() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            applicationInfo.flags = ApplicationInfo.FLAG_DEBUGGABLE
            if(0 != applicationInfo.flags){
                Log.i("Your app", "Enabling web debugging")
                WebView.setWebContentsDebuggingEnabled(true)
            }
        }

        behavior = BottomSheetBehavior.from(main_adwebview)
        initWebView()
    }

    private fun initListener() {
        btn_expand.setOnClickListener { behavior?.state = BottomSheetBehavior.STATE_EXPANDED }
        behavior?.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {
            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_EXPANDED -> {
                        main_adwebview.loadUrl(url)
                    }
                    BottomSheetBehavior.STATE_COLLAPSED -> {
                    }
                    else -> {
                    }
                }
            }
        })
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView() {
        val settings = main_adwebview.settings
        settings.javaScriptEnabled = true
        settings.allowContentAccess = true
        settings.setAppCacheEnabled(true)
        // 开启 DOM storage API 功能
        settings.domStorageEnabled = true
        main_adwebview.webViewClient = AdWebViewClient()
        main_adwebview.webChromeClient = AdWebChromeClient()

    }
}
