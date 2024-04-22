package dadm.hsingh.horoscopoapp.data.horoscope

import dadm.hsingh.horoscopoapp.domain.model.MonthlyHoroscope

interface MonthlyHoroscopeRepository {
    suspend fun get( lenguaje : String ): Result<MonthlyHoroscope>

}