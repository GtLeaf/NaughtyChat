package com.cmd.hit.im.enity

import com.cmd.hit.main.model.entry.Params

/*
* 参数传递进来后，不希望再被传递出去
* */
class ImParams(
    val count: Int
) : Params() {
}