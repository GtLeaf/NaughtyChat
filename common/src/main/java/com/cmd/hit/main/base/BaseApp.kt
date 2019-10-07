package com.cmd.hit.main.base

import android.app.Application

abstract class BaseApp : Application(){
    /*
    * appliction初始化
    * */
    abstract fun initApp(application: Application)
}