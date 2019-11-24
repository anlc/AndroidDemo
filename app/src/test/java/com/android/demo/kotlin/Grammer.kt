package com.android.demo.kotlin

import kotlin.properties.Delegates
import kotlin.reflect.KProperty

/**
 * <p>
 *
 * @author v_anlaochou
 * @date 2019-11-06
 */

fun main() {
    var notNull: String by Delegates.notNull()

    notNull = "123"
    println(notNull)
}

fun main6() {
    val site = Site(mapOf("name" to "abc", "value" to "123", "321" to "lkj"))

    println(site.name)
    println(site.value)
    println(site.map)
}

class Site(val map: Map<String, Any?>) {
    val name: String by map
    val value: String by map
}

fun Site.extension(){

}

fun main5() {
    val p = People()
    p.name = "first"
    p.name = "second"
}

class People {
    var name: String by Delegates.observable("old") { prop, old, new ->
        println("旧值：$old -> 新值：$new")
    }
}

val lazyValue: String by lazy { "Hello" }

fun main2() {
    var e = Example()
    println(e.p)

    e.p = "Hello"
    println(e.p)
}

class Example {
    var p: String by Delegate()
}

class Delegate {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "$thisRef, 这里委托了 ${property.name} 属性"
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$thisRef 的 ${property.name} 属性赋值为 $value")
    }
}

fun main1() {
    Derived(BaseImpl()).print()
}

interface Base {
    fun print()
}

class BaseImpl : Base {
    override fun print() {
        println("print")
    }
}

class Derived(b: Base) : Base by b


fun testMethod(expr: Expr) {
    when (expr) {
        is Const -> println(expr.number)
        is Sum -> println(expr.temp)
    }
}

sealed class Expr

data class Const(val number: Double) : Expr()

data class Sum(val temp: Int) : Expr()


class User(val name: String)

fun User.print() {
    println("name is $name")
}

fun newArray() {
    val a = arrayOf(1)
    val b = Array(10) { i -> (i * 2) }
}

fun getString2(obj: Any): Int? {
    if (obj is String && obj.length > 0) {
        return obj.length
    }
    return null
}

fun getString(obj: Any): Int? {

    // 做过类型判断以后，obj会被系统自动转换为String类型
    if (obj is String) {
        return obj.length
    }

    // 这里的obj仍然是Any类型的引用
    // return obj.length

    if (obj !is String) {
        // 在这里还有一种方法，与Java中instanceof不同，使用!is
        return null
    }

    // 在这个分支中, `obj` 的类型会被自动转换为 `String`
    return obj.length
}

fun nullTest2() {

    val x = parseInt()
    val y = parseInt()

    // 提示编译错误
    // println(x * y)

    if (x != null && y != null) {
        println(x * y)
    }
}

// 返回null的方法
fun parseInt(): Int? {
    return null
}

fun nullTest() {
    var a: String? = null

    // 变量后加 ？号，不会抛异常，返回值为null
    println(a?.toInt())

    // a ?: -1，做空判断处理，a为null，返回 -1
    println(a?.toInt() ?: -1)

    // 抛出空指针异常
    println(a!!.toInt())

}

fun stringTest() {
    var a = 1
    val str = "a is $a"
    a = 2
    print("${str.replace("is", "was")}, but now is $a")
}