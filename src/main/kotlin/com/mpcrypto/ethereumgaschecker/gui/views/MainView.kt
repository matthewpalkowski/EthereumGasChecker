package com.mpcrypto.ethereumgaschecker.gui.views

import com.mpcrypto.ethereumgaschecker.app.Styles
import com.mpcrypto.ethereumgaschecker.gui.controllers.MainViewController
import com.mpcrypto.ethereumgaschecker.constants.*
import javafx.beans.property.SimpleStringProperty

import javafx.geometry.Pos
import tornadofx.*

class MainView : View(StringConstants.APPLICATION_NAME) {

    //TODO Convert to FXML

    private val controller : MainViewController by inject()

    private var startButtonLabel = SimpleStringProperty()

    override val root = vbox {
        alignment = Pos.CENTER
        spacing = NumericalConstants.MAIN_SPACING

        label(title) {
            addClass(Styles.heading)
        }

        button(){
            bind(startButtonLabel)
            startButtonLabel.set(StringConstants.START_BUTTON)
            action{
                controller.scanClicked(startButtonLabel)
            }
        }

        button(StringConstants.SET_PREFERENCES_BUTTON){
            controller.preferencesClicked()
        }
    }
}