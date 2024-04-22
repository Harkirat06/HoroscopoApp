package dadm.hsingh.horoscopoapp.data.horoscope.model

import com.squareup.moshi.JsonClass
import dadm.hsingh.horoscopoapp.domain.model.DailyHoroscope

@JsonClass(generateAdapter = true)
data class DailyHoroscopeDto(
    val dailyHoroscopeText : String,
    val dayOfWeek: Int,
){
    fun toDomain() = DailyHoroscope(dailyHoroscopeText = dailyHoroscopeText, dayOfWeek = dayOfWeek)
}
