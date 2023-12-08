package com.flexath.newsify.data.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.flexath.newsify.data.manager.LocalUserManagerImpl.PreferencesKeys.APP_ENTRY
import com.flexath.newsify.domain.manager.LocalUserManager
import com.flexath.newsify.util.Constants
import com.flexath.newsify.util.Constants.USER_SETTING
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalUserManagerImpl(
    private val context: Context
) : LocalUserManager {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = USER_SETTING)

    private object PreferencesKeys {
        val APP_ENTRY = booleanPreferencesKey(Constants.APP_ENTRY_SETTING)
    }

    override suspend fun saveAppEntry() {
        context.dataStore.edit {
            it[APP_ENTRY] = true
        }
    }

    override fun readAppEntry(): Flow<Boolean> {
        return context.dataStore.data.map {
            it[APP_ENTRY] ?: false
        }
    }
}