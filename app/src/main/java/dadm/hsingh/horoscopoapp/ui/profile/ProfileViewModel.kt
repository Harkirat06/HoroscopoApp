package dadm.hsingh.horoscopoapp.ui.profile

import androidx.lifecycle.ViewModel
import dadm.hsingh.horoscopoapp.data.horoscope.daily.DailyHoroscopeRepository
import dadm.hsingh.horoscopoapp.data.settings.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject() constructor(
    private val dailyRep : DailyHoroscopeRepository,
    private val settingsRepository: SettingsRepository
) : ViewModel(){
}