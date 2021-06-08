package com.mpcrypto.ethereumgaschecker.states

import com.mpcrypto.ethereumgaschecker.constants.StringConstants

class BelowThresholdState : IGasThresholdState() {
    override val notificationMessage: String = StringConstants.MESSAGE_BELOW_THRESHOLD
    override val thresholdInput: Boolean = false
}