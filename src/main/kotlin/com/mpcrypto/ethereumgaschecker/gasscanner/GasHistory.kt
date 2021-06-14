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

    /**
     * Adds a GasSnapshot to values list and ensures that list. If list is longer that the maximum allowable length,
     * oldest snapshot is removed to make room for the most recent snapshot.
     * @param snapshot - GasSnapshot to be added to valuesList.
     */
    fun addSnapshot(snapshot: GasSnapshot){
        if(valuesList.size == NumericalConstants.MAX_DURATION_THRESHOLD) valuesList.poll()
        valuesList.add(snapshot)
    }

    /**
     * Performs a linear scan of value list to validate whether the user defined thresholds for value and duration have
     * been reached.
     * @param above - Boolean value designating the whether the history should be evaluated in manner as though the
     * current gas price is above the defined threshold, or whether it is below the defined threshold. True signifies
     * that history should be evaluated as though it is currently above the gas threshold value and false signifies that
     * the history will be evaluated as though it is currently below the gas threshold value.
     * @return true if gas value are on the opposite side of the gas threshold value as defined by the above parameter,
     * for the duration specified by the durationThreshold, otherwise false.
     */
    fun thresholdsReached(above : Boolean) : Boolean{

        val referenceComparator = if(above) -1 else 1
        var valid = true
        var totalDuration : Long = 0
        var comparisonValue : Int
        val currentHistory = valuesList.toTypedArray()

        if(valuesList.size > 0) {
            for (i in currentHistory.size - 1..0) {
                totalDuration = currentHistory[currentHistory.lastIndex].timeStamp - currentHistory[i].timeStamp
                comparisonValue = currentHistory[i].gasValue.compareTo(PreferencesManager.getPreferences().gasThreshold)

                //Value found on opposite side of threshold - exit
                if (comparisonValue != referenceComparator && comparisonValue != 0) {
                    valid = false
                    break
                }

                //Exceeded threshold for duration
                if (totalDuration > PreferencesManager.getDurationThresholdMillis()) break
            }

            //Check case if all values in list were timestamped less than durationThreshold
            if (totalDuration < PreferencesManager.getDurationThresholdMillis()) valid = false
        }
        else valid = false
        return valid
    }
}