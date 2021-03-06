package com.cmd.hit.test.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.cmd.hit.test.R
import com.cmd.hit.test.itemView.MicroAppModel
import java.lang.IllegalArgumentException

class CollectionMicroAppAdapterV2
    : PagedListAdapter<MicroAppModel, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    var recentlyUsedList = ArrayList<MicroAppModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_TITLE -> TitleViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.miniapp_title_item_view, parent, false))
            TYPE_MICRO_APP -> MiniAppViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.miniapp_item_view, parent, false))
            TYPE_NO_USE_RECORD -> MiniAppViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.miniapp_item_view, parent, false))
            else -> throw IllegalArgumentException("wrong type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            TYPE_TITLE -> (holder as TitleViewHolder).bind(RECENTLY_USED_TITLE)
            TYPE_MICRO_APP -> (holder as TitleViewHolder).bind(MY_COLLECTION_TITLE)
            TYPE_NO_USE_RECORD -> (holder as TitleViewHolder).bind(MY_COLLECTION_TITLE)
            else -> {}
        }
    }


    class MiniAppViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        private var ivMiniAppIcon: ImageView = itemView.findViewById(R.id.iv_miniapp_icon)
        private var tvMiniAppName: TextView = itemView.findViewById(R.id.tv_miniapp_name)

        fun bind(microAppModel: MicroAppModel){
            tvMiniAppName.text = microAppModel.miniAppName
        }
    }

    class TitleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        private var tvMiniAppCollectionTitle: TextView = itemView.findViewById(R.id.tv_miniapp_collection_title)

        fun bind(title: String){
            tvMiniAppCollectionTitle.text = title
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

    override fun getItemViewType(position: Int): Int {
        return when {
            RECENTLY_USED_POSITION == position -> TYPE_TITLE
            position in 1..4 -> TYPE_MICRO_APP
            MY_COLLECTION_POSITION == position -> TYPE_TITLE
            position > 5 -> TYPE_MICRO_APP
            else -> TYPE_ERROR
        }
    }

    companion object{
        val DIFF_CALLBACK = MiniAppItemCallback()
        const val TYPE_ERROR = -1
        const val TYPE_TITLE = 1
        const val TYPE_MICRO_APP = 2
        const val TYPE_NO_USE_RECORD = 3

        const val RECENTLY_USED_POSITION = 0
        const val MY_COLLECTION_POSITION = 5
        const val RECENTLY_USED_TITLE = "最近使用"
        const val MY_COLLECTION_TITLE = "我的收藏"
        const val NO_USE_RECORD = "暂无使用记录"
    }
}