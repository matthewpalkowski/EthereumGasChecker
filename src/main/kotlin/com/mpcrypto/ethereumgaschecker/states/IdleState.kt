package com.mpcrypto.ethereumgaschecker.states

import com.mpcrypto.ethereumgaschecker.constants.StringConstants
import javafx.beans.property.SimpleStringProperty

class IdleState : ProgramState() {
    override fun setStringPropertyVal(stringProperty: SimpleStringProperty) {
        stringProperty.set(StringConstants.START_BUTTON)
    }
}