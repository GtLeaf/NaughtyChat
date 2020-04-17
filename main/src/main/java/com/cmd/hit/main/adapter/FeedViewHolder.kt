package com.cmd.hit.main.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.cmd.hit.main.base.EventCenter

open class FeedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var eventCenter: EventCenter? = null
}