package by.godevelopment.binlist.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

private const val HISTORY_STORE_NAME = "history_preferences"

private val Context.dataStore by preferencesDataStore(
    name = HISTORY_STORE_NAME
)

interface HistoryStoreBehaviour {

    fun getQueryList(): Flow<List<String>>

    suspend fun saveQueryList(newQueryList: List<String>)

    class DataStoreImpl @Inject constructor(
        @ApplicationContext private val appContext: Context
    ) : HistoryStoreBehaviour {

        override fun getQueryList(): Flow<List<String>> = appContext
            .dataStore
            .data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                preferences[PreferencesKeys.HISTORY_SET]?.toList() ?: emptyList()
            }

        override suspend fun saveQueryList(newQueryList: List<String>) {
            appContext
                .dataStore
                .edit { preferences ->
                    preferences[PreferencesKeys.HISTORY_SET] = newQueryList.toSet()
                }
        }

        private object PreferencesKeys {
            val HISTORY_SET = stringSetPreferencesKey("HISTORY_SET")
        }
    }
}
