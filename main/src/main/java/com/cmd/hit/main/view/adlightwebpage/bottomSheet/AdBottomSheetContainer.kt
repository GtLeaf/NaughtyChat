package com.cmd.hit.main.view.adlightwebpage.bottomSheet

import androidx.coordinatorlayout.widget.CoordinatorLayout

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.cmd.hit.main.utils.ScreenUtil

class AdBottomSheetContainer @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CoordinatorLayout(context, attrs, defStyleAttr) {

    var collapsible = true
    var behavior = BottomSheetBehavior<AdBottomSheetContainer>().apply {
        setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                //这里是bottomSheet 状态的改变
                when (newState) {
                    BottomSheetBehavior.STATE_DRAGGING -> {
                        Log.d("AdBottomSheetContainer", "dragging")
                        if (!collapsible) {
                            state = BottomSheetBehavior.STATE_EXPANDED
                        }
                        callback?.onDragging()
                    }

                    BottomSheetBehavior.STATE_EXPANDED -> {
                        Log.d("AdBottomSheetContainer", "expanded")
                        callback?.onExpanded()
                    }

                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        Log.d("AdBottomSheetContainer", "collapsed")
                        callback?.onCollapsed()
                    }

                    else -> {}
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                //这里是拖拽中的回调，根据slideOffset可以做一些动画
                callback?.onSlide()
            }
        })

        peekHeight = (ScreenUtil.getScreenHeight(context) * 0.6).toInt()
    }

    init {

        behavior.isHideable = true
        collapsible = true
        behavior.state = BottomSheetBehavior.STATE_HIDDEN
    }

    var callback: Callback? = null

    fun expand() {
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    fun collapse() {
        behavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    fun hide() {
        if (isShowing()) {
            behavior.state = BottomSheetBehavior.STATE_HIDDEN
        }
    }

    fun isExpanded(): Boolean {
        return behavior.state == BottomSheetBehavior.STATE_EXPANDED
    }

    fun isCollapsed(): Boolean {
        return behavior.state == BottomSheetBehavior.STATE_COLLAPSED
    }

    fun isShowing(): Boolean {
        return isExpanded() || isCollapsed()
    }

    fun bindBehavior(view: View) {
        (view.layoutParams as CoordinatorLayout.LayoutParams).behavior = behavior
    }

    interface Callback {
        fun onExpanded()
        fun onCollapsed()
        fun onDragging()
        fun onSlide()
    }

    companion object {
    }
}