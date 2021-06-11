package com.mpcrypto.ethereumgaschecker

import com.mpcrypto.ethereumgaschecker.constants.NumericalConstants
import com.mpcrypto.ethereumgaschecker.gui.MainView
import javafx.stage.Stage
import tornadofx.App

//FIXME - Consider abstract factory patterns to fix reduce dependencies
//FIXME - Add custom app icon for the task bar and in the system tray for notifications
/*FIXME - Allowing for a full day for duration or scanning causes timestamps to cross multiple days which cause many issues
            with timestamps. Have to reduce this duration and explain maximums to user.*/

/*TODO
 *  -Address the issue of distributing an API key
 *      -Have to have multiple keys from Etherscan//Get a pro-license
 *      -App has to query my own server that then queries Etherscan in order to shield my api key
 *  -Markdown file
 *      -Add usage instructions
 *  -JavaDocs for the public functions
 *  */

/**
 * Main class that serves as entry point into the TornadoFX application.
 * @author Matthew Palkowski
 */
class  Main: App(MainView::class){
    override fun start(stage: Stage) {
        stage.isResizable = false
        stage.height= NumericalConstants.MAIN_HEIGHT
        stage.width = NumericalConstants.MAIN_WIDTH
        super.start(stage)
    }
}