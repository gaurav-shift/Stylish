package com.example.stylish.DataLayer.local

import android.content.Context
import androidx.compose.ui.input.key.Key.Companion.I
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreferenceDataStore(private val context: Context) {

    companion object{
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("user_preferences") //creating file in internal storage of name user_prefernces
        private val IS_FIRST_TIME_LOGIN = booleanPreferencesKey("is_first_time_login") //Key1
        private val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in") //key2 always make key in capital letter only
    }

    val isFirstTimeLogin: Flow<Boolean> = context.dataStore.data.map{ preferences-> //use flow to check bar bar
        preferences[IS_FIRST_TIME_LOGIN] ?: true // starting me kuch initialise nahi kiya so null hi hoga aur null hoga to true ho hi jayega
    }

    val isLoggedIn: Flow<Boolean> = context.dataStore.data.map { preferences->
        preferences[IS_LOGGED_IN] ?: false //by default jab new device me app launch hoga to local memory me islogin false hojayega key value
    }

    suspend fun setFirstTimeLogin(isFirsttime: Boolean){
        context.dataStore.edit { preferences->
            preferences[IS_FIRST_TIME_LOGIN]=isFirsttime
        }
    }
    suspend fun setLoggedin(isLoggedin: Boolean){
        context.dataStore.edit { preferences->
            preferences[IS_LOGGED_IN]=isLoggedin
        }
    }
// above two functions will be accesesable in AuthviewModel
}