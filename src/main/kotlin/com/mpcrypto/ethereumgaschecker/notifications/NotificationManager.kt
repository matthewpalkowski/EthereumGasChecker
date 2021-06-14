package com.mpcrypto.ethereumgaschecker.notifications

/**
 * Class that manages the sending of notifications to the user.
 */
object NotificationManager {
    //TODO figure out how the user would set this up via preferences and the defaults
    private val notifiers : MutableList<INotifier> = ArrayList()

    //FIXME should be removed and addressed properly via preferences
    init{notifiers.add(SystemNotification())}

    fun addNotifier(notifier : INotifier){notifiers.add(notifier)}

    fun distributeNotifications(message: String){
        for(notifier in notifiers){notifier.sendNotification(message)}
    }

    fun removeNotifier(notifier: INotifier){if(notifiers.contains(notifier)) notifiers.remove(notifier)}
}