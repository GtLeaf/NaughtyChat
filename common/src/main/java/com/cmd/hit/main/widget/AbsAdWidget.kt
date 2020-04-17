package com.cmd.hit.main.widget

import androidx.lifecycle.Observer
import com.cmd.hit.main.base.EventData

abstract class AbsAdWidget : AbsWidget(), Observer<EventData?>, IAdWidget {

    override fun onCreate() {
        super.onCreate()
        observe()
    }

    protected open fun observe() {

    }

    override fun onChanged(t: EventData?) {

    }
}