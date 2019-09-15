package com.cmd.hit.test

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.airbnb.epoxy.EpoxyController
import com.cmd.hit.test.controller.MiniAppCollectionController
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_mini_app_collection.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    fun initView(){
        btn_miniapp_collection.setOnClickListener {
            MiniAppCollectionActivity.actionStart(this@MainActivity)
        }
    }

    companion object{
        fun actionStart(context: Context){
            context.startActivity(Intent(context, this::class.java))
        }
    }

    fun getMiniAppModel(){

    }
}
