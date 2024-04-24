package dadm.hsingh.horoscopoapp.data.settings

import kotlinx.coroutines.flow.Flow

interface SettingsRepository {

    // Username functions
    fun getYourName() : Flow<String>
    suspend fun getYourNameSnapshot() : String
    suspend fun setYourName(userName: String)

    // Language functions
    fun getLanguage() : Flow<String>
    suspend fun getLanguageSnapshot() : String
    suspend fun setLanguage(language: String)

}