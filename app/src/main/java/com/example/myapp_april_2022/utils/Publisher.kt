package com.example.myapp_april_2022.utils

private typealias Subscriber<T> = (T?) -> Unit

class Publisher<T> {

    private val subscribers: MutableSet<Subscriber<T>> = mutableSetOf()
    public var value: T? = null
        private set
    private var hasFirstValue = false

    fun subscribe(subscriber: Subscriber<T>) {
        subscribers.add(subscriber)
        if (hasFirstValue) {
            subscriber.invoke(value)
        }
    }

    fun unSubscribe(subscriber: Subscriber<T>) {
        subscribers.remove(subscriber)
    }

    fun unSubscribeAll() {
        subscribers.clear()
    }

    fun post(value: T) {
        hasFirstValue = true
        this.value = value
        subscribers.forEach { it.invoke(value) }
    }


}