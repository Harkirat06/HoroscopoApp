package dadm.hsingh.horoscopoapp.ui.horoscope.daily

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dadm.hsingh.horoscopoapp.data.horoscope.daily.DailyHoroscopeRepository
import dadm.hsingh.horoscopoapp.domain.model.DailyHoroscope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DailyViewModel @Inject() constructor(
    private val dailyRep : DailyHoroscopeRepository
) : ViewModel() {

    private val _dailyHoroscope : MutableStateFlow<DailyHoroscope?> = MutableStateFlow(null)
    val dailyHoroscope get() = _dailyHoroscope.asStateFlow()

    private val _showError : MutableStateFlow<Throwable?> = MutableStateFlow(null)
    val showError get() = _showError.asStateFlow()

    fun getDailyHoroscope() {
        viewModelScope.launch {
            dailyRep.getDailyHoroscope("leo").fold(
                onSuccess = {_dailyHoroscope.value = it},
                onFailure = {_showError.value = it}
            )
        }


    }
}