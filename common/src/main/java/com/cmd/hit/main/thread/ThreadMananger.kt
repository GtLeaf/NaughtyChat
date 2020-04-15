package com.cmd.hit.main.thread

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.Executors

object ThreadMananger {

    @JvmField
    val DISK_IO = Executors.newSingleThreadExecutor()
    @JvmField
    val NETWORK_IO = Executors.newFixedThreadPool(6)
    @JvmField
    val MAIN_THREAD = Executors.newFixedThreadPool(3)

    class MainThreadExecutor : Executor {

        private val handler = Handler(Looper.getMainLooper())
        override fun execute(command: Runnable) {
            handler.post(command)
        }
    }
}