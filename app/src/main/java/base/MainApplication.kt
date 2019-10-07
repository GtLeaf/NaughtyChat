package base

import android.app.Application
import com.cmd.hit.main.AppConfig
import com.cmd.hit.main.base.BaseApp

class MainApplication : BaseApp() {
    override fun initApp(application: Application) {
        for (moduleApp in AppConfig.moduleApps) {
            try {
                val clazz = Class.forName(moduleApp)
                val baseApp = clazz.newInstance() as BaseApp
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
        initApp(this)
    }
}