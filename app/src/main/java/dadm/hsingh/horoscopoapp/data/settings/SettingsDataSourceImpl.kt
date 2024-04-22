package dadm.hsingh.horoscopoapp.data.settings

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class SettingsDataSourceImpl @Inject constructor(val dataStore : DataStore<Preferences>) : SettingsDataSource{

    private val yourNameKey = stringPreferencesKey("your_name")
    private val languageKey = stringPreferencesKey("language_pref")

    override fun getYourName(): Flow<String> =
        dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else throw exception
        }.map { preferences ->
            preferences[yourNameKey].orEmpty()
        }

    override suspend fun getYourNameSnapshot(): String {
        val prefs = dataStore.data.first()
        return prefs[yourNameKey]?:""
    }

    override suspend fun setYourName(userName: String) {
        dataStore.edit { preferences ->
            preferences[yourNameKey] = userName
        }
    }

    override fun getLanguage(): Flow<String> =
        dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else throw exception
        }.map { preferences ->
            preferences[languageKey].orEmpty()
        }


    override suspend fun getLanguageSnapshot(): String {
        val prefs = dataStore.data.first()
        return prefs[languageKey]?:"en"
    }

    override suspend fun setLanguage(lang: String) {
        dataStore.edit { preferences ->
            preferences[languageKey] = lang
        }
    }

}