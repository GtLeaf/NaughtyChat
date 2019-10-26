package com.cmd.hit.test

class FunOverLoadTest {

    fun test(name:String, age:Int = 0, sex: String = "man"){

    }

    @JvmOverloads fun test2(name:String, age:Int = 0, sex: String = "man"){

    }
}

fun main(){
    val funOverLoadTest = FunOverLoadTest()
    funOverLoadTest.test("haha")
}