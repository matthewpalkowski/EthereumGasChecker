package com.mpcrypto.ethereumgaschecker.states

import com.mpcrypto.ethereumgaschecker.gasscanner.GasHistory
import com.mpcrypto.ethereumgaschecker.notifications.NotificationManager

/**
 * Abstract class that builds the core functionality of the State pattern utilized to send notifications to the user
 * and several properties implemented by child classes. notificationMessage specifies the message sent to the user and
 * thesholdInput is used for external conditional branching based on the current state.
 * external control flow.
 */
abstract class GasThresholdState {

    abstract val notificationMessage : String
    abstract val thresholdInput : Boolean

    //FIXME Functionality should be moved into an abstracted message broker that is triggered by the ScannerManager
    fun notifyUser(){NotificationManager.distributeNotifications(notificationMessage)}

    fun validateSnapshot(): Boolean {return GasHistory.thresholdsReached(thresholdInput)}
}