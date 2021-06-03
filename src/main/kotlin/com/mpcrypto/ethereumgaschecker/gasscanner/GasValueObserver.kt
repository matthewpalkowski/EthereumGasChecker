package com.mpcrypto.ethereumgaschecker.gasscanner

import com.mpcrypto.ethereumgaschecker.observerutils.IValueObserver

//TODO Consider removing class in favor of IValueObserver
abstract class GasValueObserver() : IValueObserver {abstract override fun update(value : Double)}