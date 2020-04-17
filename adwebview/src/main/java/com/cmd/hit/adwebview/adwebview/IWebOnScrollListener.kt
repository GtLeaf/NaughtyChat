package com.cmd.hit.adwebview.adwebview

import android.view.View

interface IWebOnScrollListener {
    fun onScrollChanged(v: View, l: Int, t: Int, oldl: Int, oldt: Int)
}