package com.mpcrypto.ethereumgaschecker.fileio

import com.google.gson.Gson
import constants.StringConstants
import java.io.FileWriter
import java.nio.file.Files
import java.nio.file.Paths

/**
 * Singleton to manage file I/O to maintain persistent user preferences.
 */
object PreferencesManager {

    private lateinit var currentPreferences : Preferences

    fun getPreferences() : Preferences{
        if (currentPreferences == null) {
            //TODO manage io exceptions
            val gson = Gson()
            val reader = Files.newBufferedReader(Paths.get(StringConstants.PATH_PREFERENCES))
            currentPreferences = gson.fromJson(reader, Preferences::class.java)
        }
        return currentPreferences
    }

    fun updatePreferences(){
        val gson = Gson()
        //TODO manage io exceptions
        gson.toJson(currentPreferences, FileWriter(StringConstants.PATH_PREFERENCES))
    }
}