package com.cmd.hit.adwebview.jsBridge

import com.cmd.hit.adwebview.jsBridge.jsBridgeInterface.IJsBridgeMethod
import com.cmd.hit.adwebview.jsBridge.jsBridgeMethod.TestJsBridgeMethod
import org.json.JSONObject

class JsBridgeHandler {
    private val jsBridgeMap = JsBridgeRegister.JS_BRIDGE_METHOD_MAP
    private val actionMap: MutableMap<String, IJsBridgeMethod> by lazy {
        mutableMapOf<String, IJsBridgeMethod>()
    }

    fun registerExtraJsBridge() {
        JsBridgeRegister.register(
            "test",
            TestJsBridgeMethod::class.java
        )
    }

    //TODO 是否将callId缓存msg更合理？
    fun invokeAction(funName: String, callId: String) {
        takeIf {
            jsBridgeMap.containsKey(funName)
        }?.apply {
            jsBridgeMap[funName]?.newInstance()?.apply {
                invoke(JSONObject(), callId)
                actionMap[callId] = this
            }
        }
    }
}