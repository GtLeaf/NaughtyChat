package com.cmd.hit.adwebview.jsBridge

import com.cmd.hit.adwebview.jsBridge.jsBridgeInterface.IJsBridgeMethod

object JsBridgeRegister {
    val JS_BRIDGE_METHOD_MAP: MutableMap<String, Class<out IJsBridgeMethod>> by lazy {
        mutableMapOf<String, Class<out IJsBridgeMethod>>()
    }

    fun register (name: String, clazz: Class<out IJsBridgeMethod>) = apply {
        JS_BRIDGE_METHOD_MAP[name] = clazz
    }
}