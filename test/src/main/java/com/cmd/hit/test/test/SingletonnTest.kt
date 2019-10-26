package com.cmd.hit.test.test

import java.util.*

class SingletonnTest {
}
class SingletonDemo private constructor() {
    companion object {
        val instance = SingletonHolder.holder
    }

    init {
        print("SingletonDemo开始构造\n")
    }

    private object SingletonHolder {
        val holder= SingletonDemo()

        init {
            print("SingletonHolder开始构造\n")
        }
    }
}

//class SingletonDemo2 private constructor() {
//
//    companion object {
//        val instance = SingletonDemo2()
//    }
//
//    init {
//        print("SingletonDemo开始构造\n")
//    }
//
//}
//
//fun main() {
//    print("初始化完毕\n")
//    val mIn = Scanner(System.`in`)
//    when(mIn.nextInt()) {
//        1 -> SingletonDemo.instance
//        2 -> SingletonDemo2.instance
//        else -> print("again")
//    }
//}
