package dadm.hsingh.horoscopoapp.data.horoscope

import dadm.hsingh.horoscopoapp.data.horoscope.model.DailyHoroscopeDto
import retrofit2.Response

interface DailyHoroscopeDataSource {
    suspend fun get( lenguaje : String ): Response<DailyHoroscopeDto>

}