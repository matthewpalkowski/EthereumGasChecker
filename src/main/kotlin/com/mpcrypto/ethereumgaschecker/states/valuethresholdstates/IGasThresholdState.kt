package com.mpcrypto.ethereumgaschecker.states.valuethresholdstates

import com.mpcrypto.ethereumgaschecker.constants.StringConstants
import com.mpcrypto.ethereumgaschecker.gasscanner.GasHistory
import fr.jcgay.notification.*

abstract class IGasThresholdState {

    abstract val notificationMessage : String
    abstract val thresholdInput : Boolean

    fun notifyUser(){
        val iconURL = this::class.java.classLoader.getResource(StringConstants.PATH_ICON)
        val icon = Icon.create(iconURL, StringConstants.ID_ICON)

        val application = Application.builder(
            StringConstants.APPLICATION_NAME,
            StringConstants.APPLICATION_NAME,
            icon)
            .build()

        val notifier : Notifier = SendNotification()
            .setApplication(application)
            .initNotifier()

        val notification = Notification.builder()
            .title(StringConstants.APPLICATION_NAME)
            .message(notificationMessage)
            .icon(icon)
            .build()

        notifier.send(notification)
    }

    fun validateSnapshot(): Boolean {return GasHistory.thresholdsReached(thresholdInput)}
}