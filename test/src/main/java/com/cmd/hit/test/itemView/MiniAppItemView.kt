package com.cmd.hit.test.itemView

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp
import com.cmd.hit.test.R
import kotlinx.android.synthetic.main.miniapp_item_view.view.*

@ModelView(autoLayout = ModelView.Size.WRAP_WIDTH_WRAP_HEIGHT)
class MiniAppItemView @JvmOverloads constructor (
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr){

    init {
        View.inflate(context, R.layout.miniapp_item_view, this)
    }

    @ModelProp
    fun setImgUrl(imgurl: String) {

    }

    @TextProp
    fun setName(name: CharSequence?) {
        tv_miniapp_name.text = name
    }

    @CallbackProp
    fun onClickListener(listener: View.OnClickListener?){
        iv_miniapp_icon.setOnClickListener(listener)
    }
}
