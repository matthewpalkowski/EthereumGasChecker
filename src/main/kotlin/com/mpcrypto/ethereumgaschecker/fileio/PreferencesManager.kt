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
    private val file = File(StringConstants.PATH_PREFERENCES)

    init{readPreferencesFromFile()}

    /**
     * Returns the current Duration Threshold as defined by the preferences file, converted to milliseconds.
     * @return Duration Threshold as defined by the preferences file, converted to milliseconds.
     */
    fun getDurationThresholdMillis() : Long{return currentPreferences.durationThreshold.toLong() * NumericalConstants.SECOND_TO_MILLIS}

    /**
     * Returns the current Scan Frequency as defined by the preferences file, converted to milliseconds.
     * @return Scan Frequency as defined by the preferences file, converted to milliseconds.
     */
    fun getFrequencyMillis() : Long{return currentPreferences.scanFrequency.toLong() * NumericalConstants.SECOND_TO_MILLIS}

    /**
     * Returns the current preferences.
     * @return Preferences object populated with values representing the current preferences set by the user.
     */
    fun getPreferences() : Preferences{return currentPreferences}

    /*FIXME - Manage file exceptions - if file cannot be found or is corrupted somehow should attempt to recreate the
       file with default values and show message warning user*/
    private fun readPreferencesFromFile(){
        val fileReader = FileReader(file)
        val gson = Gson()
        currentPreferences = gson.fromJson(fileReader, Preferences::class.java)
    }

    /**
     * Writes the current preferences to local preferences.json file.
     */
    fun updatePreferences(){
        //FIXME - Manage potential IO exceptions
        val gson = Gson()
        val writer = FileWriter(file)
        val bufferedWriter = BufferedWriter(writer)
        gson.toJson(currentPreferences, bufferedWriter)
        bufferedWriter.flush()
        bufferedWriter.close()
    }
}