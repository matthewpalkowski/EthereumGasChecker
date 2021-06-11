package com.mpcrypto.ethereumgaschecker.observerutils

/**
 * Extendable class to serve as an the Observerable class within the Observer Pattern for the value property.
 */
open class ValueObservable {
    protected lateinit var valueObservers : MutableList<IValueObserver>
    protected var value : Int = -1 //FIXME Change to generalized implementation for any numerical value

    fun attachObserver(newValueObserver: IValueObserver){valueObservers.add(newValueObserver)}

    fun detachObserver(targetObserver: IValueObserver){valueObservers.remove(targetObserver)}

    fun notifyObservers(){for(observer in valueObservers){observer.update(value)}}
}