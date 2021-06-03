package com.mpcrypto.ethereumgaschecker.gasscanner

import com.mpcrypto.ethereumgaschecker.observerutils.ValueObservable

abstract class GasScanner() : ValueObservable() {
    abstract fun queryGas()
}