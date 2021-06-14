package com.mpcrypto.ethereumgaschecker.notifications

/**
 * Class that manages the sending of notifications to the user.
 */
object NotificationManager {
    //TODO figure out how the user would set this up via preferences and the defaults
    private val notifiers : MutableList<INotifier> = ArrayList()

    //FIXME should be removed and addressed properly via preferences
    init{notifiers.add(SystemNotification())}

    /**
     * Adds notifier to list of notifiers.
     * @param notifier - INotifier object to be added to notifiers.
     */
    fun addNotifier(notifier : INotifier){notifiers.add(notifier)}

    /**
     * Iterates through notifiers and calls .sendNotifcation() on each with the value message.
     * @param message - String to be used as message body for notification sent to the user.
     */
    fun distributeNotifications(message: String){
        for(notifier in notifiers){notifier.sendNotification(message)}
    }

    /**
     * Removes first instance of notifier from Notifiers, if present.
     * @param notifier - INotifier object to be removed from notifiers.
     */
    fun removeNotifier(notifier: INotifier){if(notifiers.contains(notifier)) notifiers.remove(notifier)}
}