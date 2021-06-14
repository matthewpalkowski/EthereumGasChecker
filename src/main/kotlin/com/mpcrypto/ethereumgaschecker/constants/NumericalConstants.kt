package com.mpcrypto.ethereumgaschecker.constants

/**
 * Class to serve as a global access point for numerical constants
 */
class NumericalConstants {
    companion object{
        //Doubles
        const val MAIN_WIDTH = 315.0
        const val MAIN_HEIGHT = 285.0
        const val MAX_GAS = 10000

        //Integers
        const val SECOND_TO_MILLIS = 1000
        const val MAX_DURATION_THRESHOLD = 86400 //currently 1 day in seconds
    }
}