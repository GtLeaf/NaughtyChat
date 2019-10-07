package com.cmd.hit.im.ui.activity

class TestClass(mcontext: String, id: Int, postion: Int) {
    val context  = mcontext

    init{
    }

    constructor(mcontext: String, id: Int): this(mcontext, id, 0){
        println("constructor 2")
    }

    constructor(mcontext: String): this(mcontext, 0){
        println("constructor 1")
    }

    fun a(){

    }
}

fun main(){
    val test = TestClass("hahaha")
}