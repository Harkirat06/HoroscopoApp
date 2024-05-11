package dadm.hsingh.horoscopoapp.ui.horoscope.daily

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dadm.hsingh.horoscopoapp.data.horoscope.daily.DailyHoroscopeRepository
import dadm.hsingh.horoscopoapp.data.settings.SettingsRepository
import dadm.hsingh.horoscopoapp.domain.model.DailyHoroscope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DailyViewModel @Inject() constructor(
    private val dailyRep : DailyHoroscopeRepository,
    private val settingsRepository: SettingsRepository
) : ViewModel() {

    private val _dailyHoroscope : MutableStateFlow<DailyHoroscope?> = MutableStateFlow(null)
    val dailyHoroscope get() = _dailyHoroscope.asStateFlow()

    private val _showError : MutableStateFlow<Throwable?> = MutableStateFlow(null)
    val showError get() = _showError.asStateFlow()

    private val _language : MutableStateFlow<String> = MutableStateFlow("")
    val language get() = _language.asStateFlow()

    fun getDailyHoroscope() {
        viewModelScope.launch {
            dailyRep.getDailyHoroscope("leo").fold(
                onSuccess = {_dailyHoroscope.value = it},
                onFailure = {_showError.value = it}
            )
        }

    }

    fun getLanguage() {
        viewModelScope.launch {
            settingsRepository.getLanguage().collect { languageCode ->
                _language.update {
                    languageCode.ifEmpty{ "en" }
                }
            }
        }
    }
}