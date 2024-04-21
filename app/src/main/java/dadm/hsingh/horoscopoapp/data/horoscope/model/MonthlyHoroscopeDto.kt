package dadm.hsingh.horoscopoapp.data.horoscope.model

import com.squareup.moshi.JsonClass
import dadm.hsingh.horoscopoapp.domain.model.MonthlyHoroscope

@JsonClass(generateAdapter = true)

data class MonthlyHoroscopeDto(
    val monthlyHoroscopeText : String,
    val month: Int,
) {
    fun toDomain() = MonthlyHoroscope(monthlyHoroscopeText = monthlyHoroscopeText, month = month)
}