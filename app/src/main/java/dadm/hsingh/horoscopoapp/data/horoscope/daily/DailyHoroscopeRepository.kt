package dadm.hsingh.horoscopoapp.data.horoscope.daily

import dadm.hsingh.horoscopoapp.domain.model.DailyHoroscope
import dagger.Provides

interface DailyHoroscopeRepository {

    suspend fun getDailyHoroscope( sign : String ): Result<DailyHoroscope>

}