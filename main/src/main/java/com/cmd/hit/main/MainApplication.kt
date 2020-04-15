package com.cmd.hit.main

import android.app.Application
import com.cmd.hit.main.base.BaseApp
import com.cmd.hit.main.other.PhotoCacheHelper

class MainApplication : BaseApp() {
    override fun initApp(application: Application) {
        mContext?.let {
            PhotoCacheHelper.init(application)
        }
    }

    override fun onTerminate() {
        super.onTerminate()
        PhotoCacheHelper.getInstance().clearDiskMemory()
    }
}