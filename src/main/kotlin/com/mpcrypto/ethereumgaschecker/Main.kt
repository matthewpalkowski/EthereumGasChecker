package com.mpcrypto.ethereumgaschecker

import com.mpcrypto.ethereumgaschecker.constants.NumericalConstants
import com.mpcrypto.ethereumgaschecker.gui.MainView
import javafx.stage.Stage
import tornadofx.App

//FIXME - Consider abstract factory patterns to fix reduce dependencies

/*TODO
 *  -Scanning thread not cleaning up properly on exit of application
 *  -Address the issue of distributing an API key
 *      -Have to have multiple keys from Etherscan//Get a pro-license
 *      -App has to query my own server that then queries Etherscan in order to shield my api key
 *  */

/**
 * Main class that serves as entry point into the TornadoFX application.
 * @author Matthew Palkowski
 */
class  Main: App(MainView::class){
    /**
     * Function to start the torandofx gui.
     */
    override fun start(stage: Stage) {
        stage.isResizable = false
        stage.height= NumericalConstants.MAIN_HEIGHT
        stage.width = NumericalConstants.MAIN_WIDTH
        super.start(stage)
    }
}