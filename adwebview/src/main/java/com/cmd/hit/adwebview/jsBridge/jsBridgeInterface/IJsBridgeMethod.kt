package com.cmd.hit.adwebview.jsBridge.jsBridgeInterface

import org.json.JSONObject

interface IJsBridgeMethod {
    fun invoke(msg: JSONObject, callId: String)
}