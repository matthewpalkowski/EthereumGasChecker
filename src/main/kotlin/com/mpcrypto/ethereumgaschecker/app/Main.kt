package com.mpcrypto.ethereumgaschecker.app

import com.mpcrypto.ethereumgaschecker.constants.NumericalConstants
import com.mpcrypto.ethereumgaschecker.gui.MainView
import javafx.stage.Stage
import tornadofx.App

//TODO ADD JAVADOCS
class  Main: App(MainView::class){
    override fun start(stage: Stage) {
        stage.isResizable = false
        stage.height= NumericalConstants.MAIN_HEIGHT
        stage.width = NumericalConstants.MAIN_WIDTH
        super.start(stage)
    }
}

/*TODO
 *  -Address the issue of distributing an API key
 *      -Have to have multiple keys from Etherscan//Get a pro-license
 *      -App has to query my own server that then queries Etherscan in order to shield my api key
 *  -Markdown file
        -Add usage instructions
 *  -Check for user pref threshold value for gas
 *      -If not available, prompt user
 *      -else run
 *  -Figure out how to update manually
 *  -Figure out interval between calls
 * */

/*TODO Map out program flow
*   -Bring up UI
*       -Start
*           -new thread begins program and animation to show the program is running (if visible?)
*           -still leaves open thread to modify notification threshold and interval
*       -Update preferences
*           -Brings up another window with preferences
*               -values are updated when user enters and applies
* */

//TODO Check the operating system of the user, set notification type accordingly

/*TODO add the below code to a "Main" thread
            val notification = Windows10Notification()
            val observer = GasValueObserver(notification)
            val ether = Etherscan()
            ether.attachObserver(observer)
            ether.queryGas()
*/
