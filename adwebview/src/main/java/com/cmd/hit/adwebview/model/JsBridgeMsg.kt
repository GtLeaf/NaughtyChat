package com.cmd.hit.adwebview.model

import org.json.JSONObject

data class JsBridgeMsg(
    val callId: String,
    val extra: JSONObject
) {

}