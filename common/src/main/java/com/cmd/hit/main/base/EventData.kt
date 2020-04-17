package com.cmd.hit.main.base

class EventData (val key: Any, private val data: Any? = null) {
    fun <T> getData(): T? {
        return if (data == null) null else data as? T
    }
}