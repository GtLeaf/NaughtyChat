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
import com.cmd.hit.test.itemView.MiniAppModel
import java.lang.IllegalArgumentException

class CollectionMiniAppAdapterV2
    : PagedListAdapter<MiniAppModel, RecyclerView.ViewHolder>(mDiffCallback) {

    var recentlyUsedList = ArrayList<MiniAppModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_TITLE -> TitleViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.miniapp_title_item_view, parent, false))
            TYPE_MINIAPP -> MiniAppViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.miniapp_item_view, parent, false))
            else -> throw IllegalArgumentException("wrong type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            TYPE_TITLE -> (holder as TitleViewHolder).bind(RECENTLY_USED_TITLE)
            TYPE_MINIAPP -> (holder as TitleViewHolder).bind(MY_COLLECTION_TITLE)

        }
    }


    class MiniAppViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        private var ivMiniAppIcon: ImageView = itemView.findViewById(R.id.iv_miniapp_icon)
        private var tvMiniAppName: TextView = itemView.findViewById(R.id.tv_miniapp_name)

        fun bind(miniAppModel: MiniAppModel){
            tvMiniAppName.text = miniAppModel.miniAppName
        }
    }

    class TitleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        private var tvMiniAppCollectionTitle: TextView = itemView.findViewById(R.id.tv_miniapp_collection_title)

        fun bind(title: String){
            tvMiniAppCollectionTitle.text = title
        }
    }

    class MiniAppItemCallback : DiffUtil.ItemCallback<MiniAppModel>() {
        override fun areItemsTheSame(oldItem: MiniAppModel, newItem: MiniAppModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MiniAppModel, newItem: MiniAppModel): Boolean {
            return oldItem == newItem
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            RECENTLY_USED_POSITION == position -> TYPE_TITLE
            position in 1..4 -> TYPE_MINIAPP
            MY_COLLECTION_POSITION == position -> TYPE_TITLE
            position > 5 -> TYPE_MINIAPP
            else -> TYPE_ERROR
        }
    }

    companion object{
        val mDiffCallback = MiniAppItemCallback()
        const val TYPE_ERROR = -1
        const val TYPE_TITLE = 1
        const val TYPE_MINIAPP = 2
        const val RECENTLY_USED_POSITION = 0
        const val MY_COLLECTION_POSITION = 5
        const val RECENTLY_USED_TITLE = "最近使用"
        const val MY_COLLECTION_TITLE = "我的收藏"
    }
}