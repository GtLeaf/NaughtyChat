package com.cmd.hit.main.utils

import android.app.Activity
import android.content.Context
import android.util.DisplayMetrics

object ScreenUtil {

    private var screenWidth = -1
    private var screenHeight = -1
    private val I_CONSTANTS_64 = 64

    /**
     * @param context 这个context应该传递Activity类型的context，如果是其他类型的context可能会导致获取的结果不对
     */
    fun getScreenHeight(context: Context?): Int {
        if (screenHeight > 0) {
            return screenHeight
        }
        if (context == null) {
            return 0
        }
        initScreenSize(context)
        return if (screenHeight > 0) screenHeight else 0
    }

    /**
     * 获取屏幕宽度
     */
    fun getScreenWidth(context: Context?): Int {
        if (screenWidth > 0) {
            return screenWidth
        }
        if (context == null) {
            return 0
        }
        initScreenSize(context)
        return if (screenWidth > 0) screenWidth else 0
    }

    private fun initScreenSize(context: Context) {
        val dm = DisplayMetrics()
        (context as Activity).windowManager.defaultDisplay.getMetrics(dm)
        screenWidth = dm.widthPixels
        screenHeight = dm.heightPixels
    }

    /**
     * dp -> pix
     */
    fun dip2Px(context: Context?, dipValue: Float): Float {
        if (context != null) {
            val scale = context.resources.displayMetrics.density
            return dipValue * scale + 0.5f
        }
        return 0f
    }
}