package com.mpcrypto.ethereumgaschecker.gasscanner

import com.mpcrypto.ethereumgaschecker.observerutils.ValueObservable

//TODO Consider whether this can be better abstracted to queryValue or something similar
abstract class GasScanner() : ValueObservable() {abstract fun queryGas()}