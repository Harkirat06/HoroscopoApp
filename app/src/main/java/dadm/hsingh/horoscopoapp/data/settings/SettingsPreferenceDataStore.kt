package dadm.hsingh.horoscopoapp.data.settings

import androidx.preference.PreferenceDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class SettingsPreferenceDataStore
@Inject constructor(val settingsRepo: SettingsRepository)
    : PreferenceDataStore() {
    override fun putString(key: String?, value: String?) {
        if (key == "your_name" ) {
            CoroutineScope(Dispatchers.IO).launch {
                settingsRepo.setYourName(value ?: "")
            }
        } else
            if (key == "language_pref") {
                CoroutineScope(Dispatchers.IO).launch {
                    settingsRepo.setLanguage(value ?: "en")
                }
            }
    }

    override fun getString(key: String?, defValue: String?): String? {
        return when (key) {
            "your_name" -> runBlocking<String>(Dispatchers.IO) {
                settingsRepo.getYourNameSnapshot()
            }
            "language_pref" -> runBlocking<String>(Dispatchers.IO) {
                settingsRepo.getLanguageSnapshot()
            }
            else -> null
        }
    }

}