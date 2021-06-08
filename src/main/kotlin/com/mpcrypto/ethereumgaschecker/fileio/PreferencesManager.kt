package com.mpcrypto.ethereumgaschecker.fileio

import com.google.gson.Gson
import com.mpcrypto.ethereumgaschecker.constants.NumericalConstants
import com.mpcrypto.ethereumgaschecker.constants.StringConstants
import java.io.*

/**
 * Singleton to manage file I/O to maintain persistent user preferences.
 */
object PreferencesManager {

    //FIXME need to make this thread-safe
    private lateinit var currentPreferences : Preferences

    fun getDurationThresholdMillis() : Long{return currentPreferences.durationThreshold.toLong() * NumericalConstants.SECOND_TO_MILLIS}

    fun getFrequencyMillis() : Long{return currentPreferences.scanFrequency.toLong() * NumericalConstants.SECOND_TO_MILLIS}

    fun getPreferences() : Preferences{
        if (!::currentPreferences.isInitialized) {
            //TODO manage io exceptions
            val file = this::class.java.classLoader.getResource(StringConstants.PATH_PREFERENCES).file
            val fileReader = FileReader(file)
            val gson = Gson()
            currentPreferences = gson.fromJson(fileReader, Preferences::class.java)
        }
        return currentPreferences
    }

    fun updatePreferences(){
        val gson = Gson()
        //FIXME Have to build the filepath correctly - currently crashes
        //TODO manage io exceptions
        gson.toJson(currentPreferences, FileWriter(StringConstants.PATH_PREFERENCES))
    }
}