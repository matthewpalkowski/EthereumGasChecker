package com.mpcrypto.ethereumgaschecker.states

import com.mpcrypto.ethereumgaschecker.constants.StringConstants

/**
 * Extension of the GasThreshold state that is specific to the case when the gas price is currently below the thresholds
 * specified in the preferences provided by the user.
 */
class BelowThresholdState : GasThresholdState() {
    override val notificationMessage: String = StringConstants.MESSAGE_ABOVE_THRESHOLD
    override val thresholdInput: Boolean = false
}