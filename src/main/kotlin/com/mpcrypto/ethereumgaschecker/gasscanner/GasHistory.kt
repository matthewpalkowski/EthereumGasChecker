package com.mpcrypto.ethereumgaschecker.gasscanner

import com.mpcrypto.ethereumgaschecker.constants.NumericalConstants
import com.mpcrypto.ethereumgaschecker.fileio.*
import java.util.*
import java.util.concurrent.LinkedBlockingQueue

/**
 * Singleton class that maintains a record gas prices and assigns time stamps for when they were recorded within the
 * record. This record is used to evaluate whether the gas prices threshold and duration threshold targeted by the user
 * via their preferences has been reached.
 */
object GasHistory {

    private val valuesList : Queue<GasSnapshot> = LinkedBlockingQueue(NumericalConstants.MAX_DURATION_THRESHOLD)

    fun addSnapshot(snapshot: GasSnapshot){
        if(valuesList.size == NumericalConstants.MAX_DURATION_THRESHOLD) valuesList.poll()
        valuesList.add(snapshot)
    }

    fun thresholdsReached(above : Boolean) : Boolean{

        val referenceComparator = if(above) -1 else 1
        var valid = true
        var totalDuration : Long = 0
        var comparisonValue : Int
        val currentHistory = valuesList.toTypedArray()

        for(i in currentHistory.size-1..0){
            totalDuration = currentHistory[currentHistory.lastIndex].timeStamp - currentHistory[i].timeStamp
            comparisonValue = currentHistory[i].gasValue.compareTo(PreferencesManager.getPreferences().gasThreshold)

            //Value found on opposite side of threshold - exit
            if(comparisonValue != referenceComparator && comparisonValue != 0){
                valid = false
                break
            }

            //Exceeded threshold for duration
            if(totalDuration > PreferencesManager.getDurationThresholdMillis()) break
        }

        //Check case if all values in list were timestamped less than durationThreshold
        if(totalDuration > PreferencesManager.getDurationThresholdMillis()) valid = false

        return valid
    }
}