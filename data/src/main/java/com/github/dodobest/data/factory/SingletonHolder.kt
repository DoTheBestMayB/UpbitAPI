package com.github.dodobest.data.factory

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
}