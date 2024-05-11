package dadm.hsingh.horoscopoapp.ui.horoscope.monthly

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dadm.hsingh.horoscopoapp.data.horoscope.monthly.MonthlyHoroscopeRepository
import dadm.hsingh.horoscopoapp.data.settings.SettingsRepository
import dadm.hsingh.horoscopoapp.domain.model.MonthlyHoroscope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MonthlyViewModel @Inject() constructor(
    private val monthlyRep : MonthlyHoroscopeRepository,
    private val settingsRepository: SettingsRepository
) : ViewModel() {

    private val _monthlyHoroscope : MutableStateFlow<MonthlyHoroscope?> = MutableStateFlow(null)
    val monthlyHoroscope get() = _monthlyHoroscope.asStateFlow()

    private val _showError : MutableStateFlow<Throwable?> = MutableStateFlow(null)
    val showError get() = _showError.asStateFlow()

    private val _language : MutableStateFlow<String> = MutableStateFlow("")
    val language get() = _language.asStateFlow()

    fun getMonthlyHoroscope() {
        viewModelScope.launch {
            monthlyRep.getMonthlyHoroscope("leo").fold(
                onSuccess = {_monthlyHoroscope.value = it},
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