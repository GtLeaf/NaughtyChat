package com.cmd.hit.main.view.adlightwebpage

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.cmd.hit.main.R
import kotlinx.android.synthetic.main.main_web_page_title_bar.view.*

class AdWebPageTitleBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    var titleBarListener: TitleBarListener? = null

    init {
        View.inflate(context, R.layout.main_web_page_title_bar, this)
        initListener()
    }

    private fun initListener() {
        pop_up_web_go_back.setOnClickListener { titleBarListener?.onClickGoBack() }
        pop_up_web_report.setOnClickListener { titleBarListener?.onClickReport() }
        pop_up_web_close_all.setOnClickListener { titleBarListener?.onClickCloseAllPage() }
    }

    interface TitleBarListener {
        fun onClickCloseAllPage()
        fun onClickGoBack()
        fun onClickReport()
        fun onClickShare()          //对齐左滑落地页预留接口
        fun onClickMenu(view: View) //同上
    }
}