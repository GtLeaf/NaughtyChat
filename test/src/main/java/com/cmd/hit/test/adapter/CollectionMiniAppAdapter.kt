package com.cmd.hit.test.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.cmd.hit.test.R
import com.cmd.hit.test.itemView.MicroAppModel

class CollectionMiniAppAdapter
    : PagedListAdapter<MicroAppModel, CollectionMiniAppAdapter.ViewHolder>(mDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        private var ivMiniAppIcon: ImageView = itemView.findViewById(R.id.iv_miniapp_icon)
        private var tvMiniAppName: TextView = itemView.findViewById(R.id.tv_miniapp_name)

        fun bind(microAppModel: MicroAppModel){
            tvMiniAppName.text = microAppModel.miniAppName
        }
    }

    class MiniAppItemCallback : DiffUtil.ItemCallback<MicroAppModel>() {
        override fun areItemsTheSame(oldItem: MicroAppModel, newItem: MicroAppModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MicroAppModel, newItem: MicroAppModel): Boolean {
            return oldItem == newItem
        }
    }

    companion object{
        val mDiffCallback = MiniAppItemCallback()
    }
}