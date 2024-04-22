package dadm.hsingh.horoscopoapp.data.settings

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SettingsRepositoryImpl
@Inject constructor(private val dataSource: SettingsDataSource)
    : SettingsRepository
{
    override fun getYourName(): Flow<String> = dataSource.getYourName()

    override suspend fun getYourNameSnapshot(): String = dataSource.getYourNameSnapshot()

    override suspend fun setYourName(userName: String) {
        dataSource.setYourName(userName)
    }

    override fun getLanguage(): Flow<String> = dataSource.getLanguage()

    override suspend fun getLanguageSnapshot(): String = dataSource.getLanguageSnapshot()

    override suspend fun setLanguage(lang: String) {
        dataSource.setLanguage(lang)
    }

}