package com.cmd.hit.main.base

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.*
import java.util.HashMap
import java.util.concurrent.atomic.AtomicInteger

class EventCenter : ViewModel() {
    private var mainThread: Thread? = null
    private val handler = Handler(Looper.getMainLooper())
    private val mDataStore = HashMap<Any, Any?>()
    private val mLiveDataMap = HashMap<Any, MutableLiveData<EventData?>>()
    private var mLifecycleOwner: LifecycleOwner? = null

    var onGoingBind = AtomicInteger(0)

    private fun isMainThread(): Boolean {
        if (mainThread == null) {
            mainThread = Looper.getMainLooper().thread
        }
        return Thread.currentThread() === mainThread
    }

    fun put(key: Any, data: Any?) : EventCenter {
        if (!isMainThread()) {
            handler.post {
                put(key, data)
            }
        }

        synchronized(this) {
            mDataStore[key] = data
            val liveData = mLiveDataMap[key]
            liveData?.value = EventData(key, data)
        }

        return this
    }

    //todo 存在父类不能转成子类的问题
    fun <T> get(key: Any): T? {
        return mDataStore[key] as? T
    }

    fun observe(key: Any, observer: Observer<EventData?>) : EventCenter {
        val liveData = getLiveData(key)
        mLifecycleOwner?.let {
            liveData?.observe(it, observer)
        }
        return this
    }

    fun getLiveData(key: Any): MutableLiveData<EventData?> {
        var liveData = mLiveDataMap[key]
        if (liveData == null) {
            liveData = MutableLiveData()
            if (mLiveDataMap.containsKey(key)) {
                liveData.value = EventData(key, mDataStore[key])
            }
            mLiveDataMap[key] = liveData
        }

        return liveData
    }

    override fun onCleared() {
        super.onCleared()
        mDataStore.clear()
        mLiveDataMap.clear()
        mLifecycleOwner = null
    }

    companion object {
        @JvmStatic
        fun create(provider: ViewModelProvider, lifecycleOwner: LifecycleOwner): EventCenter {
            val eventCenter = provider.get(EventCenter::class.java)
            eventCenter.mLifecycleOwner = lifecycleOwner
            return eventCenter
        }
    }
}