package com.mpcrypto.ethereumgaschecker.notifications

import com.mpcrypto.ethereumgaschecker.constants.StringConstants
import fr.jcgay.notification.*

/**
 * Class the generates local system notifications. Utilizes the fr.jcgay notification library found at
 * https://github.com/jcgay/send-notification.
 */
class SystemNotification : INotifier {
    /**
     * Sends notification with a body of message. Message will be configured based on user's Operating System. See
     * https://github.com/jcgay/send-notification for additional details on how this is done.
     * @param message - String defining the message body for the notification.
     */
    override fun sendNotification(message: String) {
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
            .message(message)
            .icon(icon)
            .build()

        notifier.send(notification)
    }
}