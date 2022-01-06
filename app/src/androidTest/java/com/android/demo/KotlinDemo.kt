package com.android.demo

import java.io.Serializable

/**
 * <p>
 *
 * @author anlc
 * @date 2019-12-16
 */

class KLazilySingleton private constructor() : Serializable {

    companion object {
        private var mInstance: KLazilySingleton? = null
            get() {
                return field ?: KLazilySingleton()
            }

        fun getInstance(): KLazilySingleton {
            return requireNotNull(mInstance)
        }
    }

    private fun readResolve(): Any {
        return getInstance()
    }
}

fun main() {
    KLazilySingleton.getInstance()
}