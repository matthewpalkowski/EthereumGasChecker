package com.mpcrypto.ethereumgaschecker.states

import com.mpcrypto.ethereumgaschecker.constants.StringConstants

class AboveThresholdState : IGasThresholdState() {
    override val notificationMessage: String = StringConstants.MESSAGE_ABOVE_THRESHOLD
    override val thresholdInput: Boolean = true
}