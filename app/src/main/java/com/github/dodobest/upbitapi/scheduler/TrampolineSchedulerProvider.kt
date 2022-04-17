package com.github.dodobest.upbitapi.scheduler

import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

class TrampolineSchedulerProvider : SchedulerProvider {
    override fun computation(): Scheduler {
        return Schedulers.trampoline()
    }

    override fun io(): Scheduler {
        return Schedulers.trampoline()
    }

    override fun ui(): Scheduler {
        return Schedulers.trampoline()
    }
}