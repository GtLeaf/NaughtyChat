package com.cmd.hit.im.base;

import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;

import com.cmd.hit.im.service.ImService;
import com.cmd.hit.main.ServiceFactory;
import com.cmd.hit.main.base.BaseApp;

import org.jetbrains.annotations.NotNull;

import cn.jpush.im.android.api.JMessageClient;

/**
 * 作者：Rance on 2016/12/20 16:49
 * 邮箱：rance935@163.com
 */
public class ImApplication extends BaseApp {
//    private static Application mInstance;
//    public static Context mContext;
    /**
     * 屏幕宽度
     */
    public static int screenWidth;
    /**
     * 屏幕高度
     */
    public static int screenHeight;
    /**
     * 屏幕密度
     */
    public static float screenDensity;

    /*@Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        mInstance = this;
        initScreenSize();
    }*/

    /**
     * 初始化当前设备屏幕宽高
     */
    private void initScreenSize() {
        DisplayMetrics curMetrics = Companion.getMContext().getResources().getDisplayMetrics();
        screenWidth = curMetrics.widthPixels;
        screenHeight = curMetrics.heightPixels;
        screenDensity = curMetrics.density;
    }

    @Override
    public void initApp(@NotNull Application application) {
        initModuleApp(application);
        initModuleData(application);
    }

    private void initModuleApp(@NotNull Application application) {
        ServiceFactory.Companion.getINSTANCE().setIImService(new ImService());
        JMessageClient.setDebugMode(true);
        JMessageClient.init(application);

    }

    public void initModuleData(@NotNull Application application) {
        initScreenSize();
    }
}
