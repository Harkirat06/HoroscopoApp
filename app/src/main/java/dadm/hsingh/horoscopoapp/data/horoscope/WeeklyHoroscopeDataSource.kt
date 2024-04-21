package dadm.hsingh.horoscopoapp.data.horoscope

import dadm.hsingh.horoscopoapp.data.horoscope.model.WeeklyHoroscopeDto
import retrofit2.Response

interface WeeklyHoroscopeDataSource {
    suspend fun get( lenguaje : String ): Response<WeeklyHoroscopeDto>

}