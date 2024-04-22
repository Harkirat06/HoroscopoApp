package dadm.hsingh.horoscopoapp.data.horoscope.model

import com.squareup.moshi.JsonClass
import dadm.hsingh.horoscopoapp.domain.model.WeeklyHoroscope

@JsonClass(generateAdapter = true)
data class WeeklyHoroscopeDto(
    val weeklyHoroscopeText : String,
    val week: Int,
) {
    fun toDomain() = WeeklyHoroscope(weeklyHoroscopeText = weeklyHoroscopeText, week = week)
}