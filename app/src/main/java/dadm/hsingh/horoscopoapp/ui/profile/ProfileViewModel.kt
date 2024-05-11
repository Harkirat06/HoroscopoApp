package dadm.hsingh.horoscopoapp.ui.profile

import androidx.lifecycle.ViewModel
import dadm.hsingh.horoscopoapp.data.horoscope.weekly.WeeklyHoroscopeRepository
import dadm.hsingh.horoscopoapp.domain.model.ZodiacSign
import dadm.hsingh.horoscopoapp.domain.model.ZodiacSigns.ZodiacSigns
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject() constructor(
) : ViewModel() {
    private val zodiacSigns = ZodiacSigns()

    fun getZodiacSign(sign: String): ZodiacSign? {
        return zodiacSigns.getZodiacSign(sign)
    }
}