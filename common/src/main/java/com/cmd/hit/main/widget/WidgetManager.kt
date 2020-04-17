package com.cmd.hit.main.widget

import android.app.Activity
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.asynclayoutinflater.view.AsyncLayoutInflater
import com.cmd.hit.main.base.EventCenter
import java.util.concurrent.CopyOnWriteArrayList

class WidgetManager {
    private val widgets = CopyOnWriteArrayList<AbsWidget>()
    private var contentView: View? = null
    private var context: Context? = null
    private var activity: AppCompatActivity? = null
    private var syncLayoutInflater: LayoutInflater? = null
    private var asyncLayoutInflater: AsyncLayoutInflater? = null
    private var mainThread: Thread? = null
    private val handler by lazy { Handler(Looper.getMainLooper()) }
    private var eventCenter: EventCenter? = null

    fun bind(@IdRes viewId: Int, widget: AbsWidget?): WidgetManager {
        if (widget == null) {
            return this
        }

        return bind(contentView?.findViewById<View>(viewId), widget)
    }

    fun bind(view: View?, widget: AbsWidget?): WidgetManager {
        if (view == null || widget == null) {
            return this
        }

        widget.mContext = context
        widget.mContentView = view
        widget.mEventCenter = eventCenter
        widgets.add(widget)
        if (isMainThread()) {
            getActivity()?.lifecycle?.addObserver(widget)
        } else {
            handler.post {
                getActivity()?.lifecycle?.addObserver(widget)
            }
        }
        return this
    }

    private fun isMainThread(): Boolean {
        if (mainThread == null) {
            mainThread = Looper.getMainLooper().thread
        }
        return Thread.currentThread() === mainThread
    }

    private fun getActivity() : AppCompatActivity? {
        return activity?: (context as? AppCompatActivity)
    }

    fun setEventCenter(eventCenter: EventCenter) {
        this.eventCenter = eventCenter
        widgets.forEach {
            it.mEventCenter = eventCenter
        }
    }

    companion object {
        @JvmStatic
        fun of(activity: AppCompatActivity, rootView: View) : WidgetManager {
            return create(activity, rootView, activity)
        }

        @JvmStatic
        fun create(activity: AppCompatActivity, rootView: View, context: Context) : WidgetManager {
            val widgetManager = WidgetManager()
            widgetManager.contentView = rootView
            widgetManager.context = context
            widgetManager.activity = activity
            widgetManager.syncLayoutInflater = LayoutInflater.from(context)
            widgetManager.asyncLayoutInflater = AsyncLayoutInflater(context)
            return WidgetManager().apply {
                this.contentView = rootView
                this.context = context
                this.syncLayoutInflater = LayoutInflater.from(context)
                this.asyncLayoutInflater = AsyncLayoutInflater(context)
            }
        }
    }
}