package dadm.hsingh.horoscopoapp.data.horoscope

import dadm.hsingh.horoscopoapp.domain.model.DailyHoroscope

interface DailyHoroscopeRepository {
    suspend fun get( lenguaje : String ): Result<DailyHoroscope>

}