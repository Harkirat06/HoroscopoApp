package dadm.hsingh.horoscopoapp.ui.horoscope.weekly

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dadm.hsingh.horoscopoapp.data.horoscope.weekly.WeeklyHoroscopeRepository
import dadm.hsingh.horoscopoapp.domain.model.WeeklyHoroscope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeeklyViewModel @Inject() constructor(
    private val weeklyRep : WeeklyHoroscopeRepository
) : ViewModel() {

    private val _weeklyHoroscope : MutableStateFlow<WeeklyHoroscope?> = MutableStateFlow(null)
    val weeklyHoroscope get() = _weeklyHoroscope.asStateFlow()

    private val _showError : MutableStateFlow<Throwable?> = MutableStateFlow(null)
    val showError get() = _showError.asStateFlow()

    fun getWeeklyHoroscope() {
        viewModelScope.launch {
            weeklyRep.getWeeklyHoroscope("leo").fold(
                onSuccess = {_weeklyHoroscope.value = it},
                onFailure = {_showError.value = it}
            )
        }
    }
}