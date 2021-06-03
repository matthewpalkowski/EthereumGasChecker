package com.mpcrypto.ethereumgaschecker.states.valuethresholdstates

import com.mpcrypto.ethereumgaschecker.constants.StringConstants
import com.mpcrypto.ethereumgaschecker.gasscanner.GasHistory

class AboveThresholdState : IGasThresholdState() {
    override val notificationMessage: String = StringConstants.MESSAGE_ABOVE_THRESHOLD
    override val thresholdInput: Boolean = true
}