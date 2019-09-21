package com.cmd.hit.test.controller

import com.airbnb.epoxy.EpoxyController
import com.cmd.hit.test.itemView.MicroAppItemViewModel_
import com.cmd.hit.test.itemView.MicroAppModel

class MiniAppCollectionController : EpoxyController() {

    var miniApps = ArrayList<MicroAppModel>()
    set(value) {
        field = value
        requestModelBuild()
    }

    private var loadingMore = true
    set(value) {
        field = value
        requestModelBuild()
    }

    override fun buildModels() {
        for (it in miniApps) {
            MicroAppItemViewModel_()
                .id("miniApp", it.id)
                .imgUrl(it.iconUrl)
                .name(it.miniAppName)
                .addTo(this)
        }
    }
}