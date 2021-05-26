package com.mpcrypto.ethereumgaschecker.gui.views

import com.mpcrypto.ethereumgaschecker.constants.StringConstants
import javafx.scene.layout.AnchorPane
import tornadofx.*

class MainView : View(StringConstants.APPLICATION_NAME) {
    override val root: AnchorPane by fxml(StringConstants.MAIN_FXML,true)
}
