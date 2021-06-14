package com.mpcrypto.ethereumgaschecker.notifications

/**
 * Interface to serve as a layer of abstraction for different type of notifications.
 */
interface INotifier {fun sendNotification(message: String)}