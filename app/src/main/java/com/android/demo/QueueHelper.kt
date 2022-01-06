package com.android.demo

import android.os.Handler
import android.os.Message
import java.lang.ref.WeakReference
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * <p>
 *
 * @author anlc
 * @date 2019-11-26
 */


fun main(){
    val helper = QueueHelper()
    helper.executorService = Executors.newSingleThreadExecutor()
}
class QueueHelper {

    private val innerHandler by lazy { InnerHandler() }
    private val queue by lazy { LinkedList<Runnable>() }
    lateinit var executorService: ExecutorService

    fun addTask(runnable: Runnable) {
        queue.add(runnable)
        postRun()
    }

    fun postRun() {
        if (queue.isEmpty()) {
            return
        }
        val message = innerHandler.obtainMessage(INTERVAL_TIME)
        message.obj = WeakReference(this)
        innerHandler.sendMessageDelayed(message, INTERVAL_TIME.toLong())
    }

    class InnerHandler : Handler() {
        override fun handleMessage(msg: Message?) {
            msg.let {
                val obj = it?.obj
                if (obj !is WeakReference<*>) {
                    return
                }
                val o = obj.get()
                if (o is QueueHelper) {
                    o.postRun()
                }
            }
        }
    }

    companion object {
        const val INTERVAL_TIME = 1000
    }
}