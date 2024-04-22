package dadm.hsingh.horoscopoapp.ui.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import dadm.hsingh.horoscopoapp.R
import dadm.hsingh.horoscopoapp.data.settings.SettingsPreferenceDataStore
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SettingsFragment : PreferenceFragmentCompat() {

    @Inject
    lateinit var settingsPreferenceDataStore: SettingsPreferenceDataStore

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        preferenceManager.preferenceDataStore = settingsPreferenceDataStore
        setPreferencesFromResource(R.xml.preferences_settings, rootKey)
    }

}