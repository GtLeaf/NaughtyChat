package com.cmd.hit.main.widget

import android.content.Context
import android.view.View
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.cmd.hit.main.base.EventCenter

abstract class AbsWidget : IWidget, LifecycleObserver {

    var mContext: Context? = null

    var mContentView: View? = null

    var mEventCenter: EventCenter? = null

    @CallSuper
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    open fun onCreate() {
        onBindView(mContentView)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
    }

    @CallSuper
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {

    }

    @LayoutRes
    protected fun getLayoutId(): Int {
        return 0
    }

    open fun onBindView(view: View?) {

    }
}