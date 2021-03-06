package com.cmd.hit.im.enity

import com.cmd.hit.main.model.entry.IBuilder
import com.cmd.hit.main.model.entry.Params

object ImBuilder : IBuilder {
    private var count = 0

    override fun build(): Params {
        return ImParams(count)
    }

    fun count(count: Int) = apply { this.count = count }
}
