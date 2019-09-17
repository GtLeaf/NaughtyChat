package com.cmd.hit.test.controller

import android.content.Context
import com.cmd.hit.test.adapter.CollectionMicroAppAdapterV2

class CollectionMicroAppController {

    lateinit var context: Context
    private lateinit var microAppAdapter: CollectionMicroAppAdapterV2

    class Builder {
        private val inst by lazy { CollectionMicroAppController() }

        fun setContext(context: Context) = apply { inst.context =  context}
        fun setMicroAppAdapter(adapter: CollectionMicroAppAdapterV2) = apply { inst.microAppAdapter = adapter }
        fun build() = inst
    }
}