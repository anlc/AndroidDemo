package com.android.demo.kotlin

/**
 * <p>
 *
 * @author v_anlaochou
 * @date 2019-11-12
 */

fun main() {
    AlsoTest().testAlso()
}

class AlsoTest {
    fun testAlso() {
        val str = "Boss"

        val result = str.run {
            println(this.substring(0, 2))
        }

        println(result)
    }
}
