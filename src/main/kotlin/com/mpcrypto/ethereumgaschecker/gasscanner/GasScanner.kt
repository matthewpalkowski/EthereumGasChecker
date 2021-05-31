package com.mpcrypto.ethereumgaschecker.gasscanner

import ValueObservable

abstract class GasScanner() : ValueObservable() {
    abstract fun queryGas()
}