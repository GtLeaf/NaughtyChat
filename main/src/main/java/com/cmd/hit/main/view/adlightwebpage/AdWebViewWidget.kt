package com.cmd.hit.main.view.adlightwebpage

import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.cmd.hit.main.base.EventData
import com.cmd.hit.main.view.AdWidgetConstants.ACTION_LIGHT_WEB_PAGE_SHOW
import com.cmd.hit.main.view.AdWidgetConstants.ON_NEW_ITEM_CLICK
import com.cmd.hit.main.widget.AbsAdWidget

class AdWebViewWidget : AbsAdWidget() {

    private var adLightWebPageView : AdLightWebPageView? = null

    override fun observe() {
        super.observe()
        mEventCenter.let {
            mEventCenter?.observe(ON_NEW_ITEM_CLICK, this)
            mEventCenter?.observe(ACTION_LIGHT_WEB_PAGE_SHOW, this)
        }
    }

    override fun onChanged(t: EventData?) {
        t?: return
        when (t.key) {
            ON_NEW_ITEM_CLICK -> {
                Log.d("AdWebViewWidget", "eventCenter test")
            }
            ACTION_LIGHT_WEB_PAGE_SHOW -> {
                show()
            }
            else -> {}
        }
    }

    override fun onBindView(view: View?) {
        super.onBindView(view)
        val context = mContext?: return

        if (adLightWebPageView == null) {
            adLightWebPageView = AdLightWebPageView(context)
            (mContentView as ViewGroup).addView(adLightWebPageView)
        }
    }

    fun show() {
        adLightWebPageView?.show()
    }
}