package com.example.arildojunior.upcomingmovies.data.bus

import io.reactivex.subjects.BehaviorSubject


class RxBus {
    companion object {
        val behaviorSubject : BehaviorSubject<Any> = BehaviorSubject.create()
        fun getSubject () : BehaviorSubject<Any> {
            return behaviorSubject
        }
    }
}