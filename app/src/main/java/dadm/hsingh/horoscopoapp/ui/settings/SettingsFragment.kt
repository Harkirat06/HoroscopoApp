package dadm.hsingh.horoscopoapp.ui.settings

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.preference.PreferenceFragmentCompat
import dadm.hsingh.horoscopoapp.R
import dadm.hsingh.horoscopoapp.data.settings.SettingsPreferenceDataStore
import dadm.hsingh.horoscopoapp.utils.AlarmService
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@AndroidEntryPoint
class SettingsFragment : PreferenceFragmentCompat() {

    private val viewModel : SettingsViewModel by  viewModels()
    private lateinit var alarmService : AlarmService
    private var notifReminder = false
    private var notifBirthdays = false

    @Inject
    lateinit var settingsPreferenceDataStore: SettingsPreferenceDataStore

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        preferenceManager.preferenceDataStore = settingsPreferenceDataStore
        setPreferencesFromResource(R.xml.preferences_settings, rootKey)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        alarmService = AlarmService(requireContext())

        // Dark mode
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.darkMode.collect { isDarkModeEnabled ->
                    if (isDarkModeEnabled) {
                        // Cambiar al tema oscuro
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    } else {
                        // Cambiar al tema claro
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    }
                }
            }
        }

        // Notificacion recordatorio
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.notificationReminder.collect { isEnabled ->
                    if (isEnabled) {
                        notifReminder = true
                        alarmService.setReminderAlarm()
                    } else {
                        notifReminder = false
                        alarmService.cancelAlarms()
                        if (notifBirthdays) {
                            setAllBirthdayAlarms()
                        }
                    }
                }
            }
        }

        // Notificaciones cumpleaños
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.notificationBirthdays.collect { isEnabled ->
                    if (isEnabled) {
                        notifBirthdays = true
                        setAllBirthdayAlarms()
                    } else {
                        notifBirthdays = false
                        alarmService.cancelAlarms()
                        if (notifReminder) {
                            alarmService.setReminderAlarm()
                        }
                    }
                }
            }
        }

    }

    fun setAllBirthdayAlarms() {
        val birthday = Calendar.getInstance().apply {// AQQUÍ TENGO QUE PONER EL TIEMPO DEL CUMPLEAÑOS
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, 3)
            set(Calendar.MINUTE, 3)
            set(Calendar.SECOND, 0)
        }
        val thisYear = Calendar.getInstance()
        thisYear.set(Calendar.DAY_OF_YEAR, 1)
        thisYear.set(Calendar.HOUR_OF_DAY, 0)
        thisYear.set(Calendar.MINUTE, 0)
        thisYear.set(Calendar.SECOND, 0)
        thisYear.set(Calendar.MILLISECOND, 0)
        val nextBirthday = thisYear.timeInMillis + birthday.timeInMillis

        alarmService.setBirthdayAlarm(nextBirthday, "Paco")
    }

}