package dadm.hsingh.horoscopoapp.data.horoscope.model

import com.squareup.moshi.JsonClass
import dadm.hsingh.horoscopoapp.domain.model.WeeklyHoroscope

@JsonClass(generateAdapter = true)
data class WeeklyHoroscopeDto(
    val data: WeeklyHoroscopeData,
    val status: Int,
    val success: Boolean
) {
    fun toDomain() = WeeklyHoroscope(weeklyHoroscopeText = data.horoscope_data, week = data.week)
}

@JsonClass(generateAdapter = true)
data class WeeklyHoroscopeData(
    val horoscope_data: String,
    val week: String
)