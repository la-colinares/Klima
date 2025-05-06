package com.lacolinares.klima.data.local.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.lacolinares.klima.domain.model.Session
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "user_session")

class SessionManager(context: Context) {

    private val dataStore = context.dataStore

    val sessionFlow: Flow<Session?> = dataStore.data.map { prefs ->
        val email = prefs[Keys.EMAIL]
        val isLoggedIn = prefs[Keys.IS_LOGGED_IN] ?: false
        if (isLoggedIn && email != null) Session(email) else null
    }

    suspend fun saveSession(email: String) {
        dataStore.edit { prefs ->
            prefs[Keys.EMAIL] = email
            prefs[Keys.IS_LOGGED_IN] = true
        }
    }

    suspend fun clearSession() {
        dataStore.edit { it.clear() }
    }

    private object Keys {
        val EMAIL = stringPreferencesKey("email")
        val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
    }
}