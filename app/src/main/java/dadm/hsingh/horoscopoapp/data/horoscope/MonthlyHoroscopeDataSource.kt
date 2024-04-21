package dadm.hsingh.horoscopoapp.data.horoscope

import dadm.hsingh.horoscopoapp.data.horoscope.model.MonthlyHoroscopeDto
import retrofit2.Response

interface MonthlyHoroscopeDataSource {
    suspend fun get( lenguaje : String ): Response<MonthlyHoroscopeDto>

}