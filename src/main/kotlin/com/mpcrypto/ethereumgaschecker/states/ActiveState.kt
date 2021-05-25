package com.mpcrypto.ethereumgaschecker.states

import com.mpcrypto.ethereumgaschecker.constants.StringConstants
import javafx.beans.property.SimpleStringProperty

class ActiveState : ProgramState() {
    override fun setStringPropertyVal(stringProperty: SimpleStringProperty) {
        stringProperty.set(StringConstants.STOP_BUTTON)
    }
}