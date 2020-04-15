package com.cmd.hit.main.base

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.cmd.hit.main.AppConfig
import java.lang.ref.WeakReference

open class CommonApplication : BaseApp() {

    private val applicationList = mutableListOf<BaseApp>()

    override fun initApp(application: Application) {
        for (moduleApp in AppConfig.moduleApps) {
            try {
                val clazz = Class.forName(moduleApp)
                val baseApp = clazz.newInstance() as BaseApp
                applicationList.add(baseApp)
                baseApp.initApp(this)
            } catch (e: ClassNotFoundException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            } catch (e: InstantiationException) {
                e.printStackTrace()
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        mContext = WeakReference(applicationContext)
        mInstance = this
        initApp(this)
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onTerminate() {
        super.onTerminate()
        applicationList.forEach { it.onTerminate() }
    }

    override fun onLowMemory() {
        super.onLowMemory()
        applicationList.forEach { it.onLowMemory() }
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        applicationList.forEach { it.onTrimMemory(level) }
    }
}