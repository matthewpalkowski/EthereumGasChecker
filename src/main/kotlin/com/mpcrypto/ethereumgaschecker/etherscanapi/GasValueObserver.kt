package com.mpcrypto.ethereumgaschecker.etherscanapi

import IValueObserver
import com.mpcrypto.ethereumgaschecker.notification.Notification

class GasValueObserver(private val notification: Notification) : IValueObserver {

    /*TODO
    *   -Figure out notification method for when the threshold has been crossed
    *       -Should only notify if threshold was crossed, not just if its above
    *       -Should somehow avoid situation where gas is fluctuating across threshold multiple times
    * */

    override fun update(value : Double) {

    }

    private fun notifyUser(){notification.pushNotification()}

}