package dadm.hsingh.horoscopoapp.data.horoscope

import dadm.hsingh.horoscopoapp.domain.model.WeeklyHoroscope

interface WeeklyHoroscopeRepository {
    suspend fun get( lenguaje : String ): Result<WeeklyHoroscope>

}