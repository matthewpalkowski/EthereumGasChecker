package com.mpcrypto.ethereumgaschecker.states.valuethresholdstates

import com.mpcrypto.ethereumgaschecker.constants.StringConstants

class BelowThresholdState : IGasThresholdState() {
    override val notificationMessage: String = StringConstants.MESSAGE_BELOW_THRESHOLD
    override val thresholdInput: Boolean = false
}