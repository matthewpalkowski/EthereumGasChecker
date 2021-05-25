package com.mpcrypto.ethereumgaschecker.fileio

import com.google.gson.Gson
import com.mpcrypto.ethereumgaschecker.constants.StringConstants
import java.io.FileWriter
import java.nio.file.Files
import java.nio.file.Paths
import java.util.prefs.Preferences

class PreferencesManager {

    companion object{
        lateinit var currentPreferences : Preferences
    }

    init {
        getPreferences()
    }

    fun getPreferences(){
        val gson = Gson()
        //TODO manage io exceptions
        val reader = Files.newBufferedReader(Paths.get(StringConstants.PREFERENCE_FILE))
        currentPreferences = gson.fromJson(reader,Preferences::class.java)
    }

    fun updatePreferences(){
        val gson = Gson()
        //TODO manage io exceptions
        gson.toJson(currentPreferences, FileWriter(StringConstants.PREFERENCE_FILE))
    }
}