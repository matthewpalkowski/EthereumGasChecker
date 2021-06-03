package com.mpcrypto.ethereumgaschecker.observerutils

open class ValueObservable {
    protected lateinit var valueObservers : MutableList<IValueObserver>
    protected var value : Double = -1.0

    fun attachObserver(newValueObserver: IValueObserver){valueObservers.add(newValueObserver)}

    fun detachObserver(targetObserver: IValueObserver){valueObservers.remove(targetObserver)}

    fun notifyObservers(){for(observer in valueObservers){observer.update(value)}}
}