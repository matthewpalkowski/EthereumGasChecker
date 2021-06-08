package com.mpcrypto.ethereumgaschecker.observerutils

/**
 * Interface to serve as an the Observer within the Observer Pattern for observing changes in the value property of the
 * ValueObservable.
 */
interface IValueObserver {fun update(value : Double)}