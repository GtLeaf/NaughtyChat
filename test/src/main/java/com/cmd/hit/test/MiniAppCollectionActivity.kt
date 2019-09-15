package com.cmd.hit.test

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.cmd.hit.test.controller.MiniAppCollectionController
import com.cmd.hit.test.itemView.MiniAppModel
import kotlinx.android.synthetic.main.activity_mini_app_collection.*
import kotlinx.android.synthetic.main.search_bar.*

class MiniAppCollectionActivity : AppCompatActivity() {

    private lateinit var collectionController: MiniAppCollectionController
    private lateinit var recentlyUsedController: MiniAppCollectionController



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mini_app_collection)
        initView()
    }

    fun initView(){

        initRecentlyUsedEpoxyRecyclerView()
        initMyCollectionEpoxyRecyclerView()

        ibtn_back.setOnClickListener {
            val miniapps = getMiniAppModel(10)
            collectionController.miniApps = miniapps
        }
    }

    fun getMiniAppModel(number: Int) = ArrayList<MiniAppModel>().apply {
        for (i in 0..number) {
            this.add(MiniAppModel(i.toLong(), "iconUrl:$i", "name:$i"))
        }
    }

    companion object{
        fun actionStart(context: Context){
            context.startActivity(Intent(context, this::class.java))
        }
    }

    fun initRecentlyUsedEpoxyRecyclerView(){
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        erv_recently_used.layoutManager = layoutManager
        recentlyUsedController =  MiniAppCollectionController()
        erv_recently_used.adapter = recentlyUsedController.adapter
        recentlyUsedController.requestModelBuild()
        recentlyUsedController.miniApps = getMiniAppModel(2)
    }

    fun initMyCollectionEpoxyRecyclerView(){
        erv_my_collection.layoutManager = GridLayoutManager(this, 4)
        collectionController = MiniAppCollectionController()
        erv_my_collection.adapter = collectionController.adapter
        collectionController.requestModelBuild()
        collectionController.miniApps = getMiniAppModel(10)
    }

    fun initMyCollectionRecyclerView (){

    }
}
