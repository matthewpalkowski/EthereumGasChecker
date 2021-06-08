package com.mpcrypto.ethereumgaschecker.states

import com.mpcrypto.ethereumgaschecker.constants.StringConstants
import com.mpcrypto.ethereumgaschecker.gasscanner.GasHistory
import fr.jcgay.notification.*

/**
 * Abstract class that builds the core functionality of the State pattern utilized to send notifications to the user
 * and several properties implemented by child classes. notificationMessage specifies the message sent to the user and
 * thesholdInput is used for external conditional branching based on the current state.
 * external control flow.
 */
abstract class GasThresholdState {

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