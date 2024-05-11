package dadm.hsingh.horoscopoapp.ui.horoscope.weekly

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dadm.hsingh.horoscopoapp.data.horoscope.weekly.WeeklyHoroscopeRepository
import dadm.hsingh.horoscopoapp.data.ranking.RankingRepository
import dadm.hsingh.horoscopoapp.domain.model.Ranking
import dadm.hsingh.horoscopoapp.domain.model.RankingItem
import dadm.hsingh.horoscopoapp.domain.model.WeeklyHoroscope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeeklyViewModel @Inject() constructor(
    private val weeklyRep : WeeklyHoroscopeRepository,
    private val rankRep : RankingRepository
) : ViewModel() {

    private val _weeklyHoroscope : MutableStateFlow<WeeklyHoroscope?> = MutableStateFlow(null)
    val weeklyHoroscope get() = _weeklyHoroscope.asStateFlow()

    private val _ranking : MutableStateFlow<Ranking?> = MutableStateFlow(null)
    val ranking get() = _ranking.asStateFlow()


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

    fun getRanking(){
        viewModelScope.launch {
            rankRep.getRanking().fold(
                onSuccess = {_ranking.value = it},
                onFailure = { _showError.value = it }
            )
        }
    }
}