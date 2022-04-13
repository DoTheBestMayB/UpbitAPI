package com.github.dodobest.domain

open class SingletonHolder<out T, in A>(val creator: (A) -> (T)) {
    @Volatile
    private var instance: T? = null

    fun getInstance(arg: A): T {
        if (instance != null) {
            return instance!!
        }

        return synchronized(this) {
            if (instance == null) {
                instance = creator(arg)
            }
            instance!!
        }
    }

    fun resetInstanceOnlyForTest() {
        if (instance == null) {
            return
        }

        synchronized(this) {
            if (instance == null) {
                return
            }
            instance = null
        }
    }
}