package com.mpcrypto.ethereumgaschecker.gasscanner

import com.mpcrypto.ethereumgaschecker.fileio.PreferencesManager

/**
 * Singleton class that maintains a record gas prices and assigns time stamps for when they were recorded within the
 * record. This record is used to evaluate whether the gas prices threshold and duration threshold targeted by the user
 * via their preferences has been reached.
 */
object GasHistory {

    //TODO need to make this list thread-safe
    private val valuesList : MutableList<GasSnapshot> = ArrayList()

    //TODO need enforce a max size of values list otherwise there will be memory leak
    fun addSnapshot(snapshot: GasSnapshot){valuesList.add(snapshot)}

    fun thresholdsReached(above : Boolean) : Boolean{

        val referenceComparator = if(above) -1 else 1
        var valid = true
        var totalDuration : Long = 0
        var comparisonValue : Int

        for(i in valuesList.size-1..0){
            totalDuration = valuesList[valuesList.lastIndex].timeStamp - valuesList[i].timeStamp
            comparisonValue = valuesList[i].gasValue.compareTo(PreferencesManager.getPreferences().gasThreshold)
            if(comparisonValue != referenceComparator && comparisonValue != 0){
                valid = false
                break
            }
            if(totalDuration > PreferencesManager.getDurationThresholdMillis()) break
        }

        if(totalDuration > PreferencesManager.getDurationThresholdMillis()) valid = false

        return valid
    }
}