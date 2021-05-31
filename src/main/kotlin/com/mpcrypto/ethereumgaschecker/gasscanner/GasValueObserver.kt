package com.mpcrypto.ethereumgaschecker.gasscanner

import IValueObserver
import com.mpcrypto.ethereumgaschecker.constants.StringConstants
import fr.jcgay.notification.*


abstract class GasValueObserver() : IValueObserver {

    /*TODO
    *   -Figure out notification method for when the threshold has been crossed
    *       -Should only notify if threshold was crossed, not just if its above
    *       -Should somehow avoid situation where gas is fluctuating across threshold multiple times
    * */

    abstract override fun update(value : Double)

    private fun notifyUser(above: Boolean){
        //TODO change to constants strings that are more descriptive
        val message = if(above) "Gas passed threshold"
            else "Gas fell below threshold"
        val iconURL = this::class.java.classLoader.getResource(StringConstants.PATH_ICON)
        val notifier : Notifier = SendNotification().initNotifier()
        val test = Notification.builder()
            .title(StringConstants.APPLICATION_NAME)
            .message(message)
            .icon(Icon.create(iconURL,StringConstants.ID_ICON))
            .build()
        notifier.send(test)
    }

}