package com.cmd.hit.main.base

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

abstract class BaseApp : Application(){

    /*
    * appliction初始化
    * */
    abstract fun initApp(application: Application)

    companion object {
        var mInstance: Application? = null
        @SuppressLint("StaticFieldLeak")
        var mContext: Context? = null
    }

}