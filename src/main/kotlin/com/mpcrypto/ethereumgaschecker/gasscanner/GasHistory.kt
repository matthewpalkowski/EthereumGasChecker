package com.mpcrypto.ethereumgaschecker.gasscanner

import com.mpcrypto.ethereumgaschecker.fileio.PreferencesManager

object GasHistory {

    private val valuesList : ArrayList<GasSnapshot> = ArrayList()

    //TODO need enfore a max size of values list otherwise there will be memory leak
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