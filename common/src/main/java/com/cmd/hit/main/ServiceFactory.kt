package com.cmd.hit.main

import com.cmd.hit.main.emptyService.EmptyImService
import com.cmd.hit.main.service.IImService

class ServiceFactory private constructor(){

    val serviceMap = HashMap<String, IImService>()
    var iImService: IImService? = null
        get() {
            if (null == field) {
                field = EmptyImService()
            }
            return field
        }

    companion object{
        val INSTANCE = Inner.serviceFactory
    }

    private object Inner {
        val serviceFactory = ServiceFactory()
    }
}