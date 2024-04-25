package dadm.hsingh.horoscopoapp.data.horoscope.model

import com.squareup.moshi.JsonClass
import dadm.hsingh.horoscopoapp.domain.model.MonthlyHoroscope

@JsonClass(generateAdapter = true)
data class MonthlyHoroscopeDto(
    val data : MonthlyHoroscopeData,
    val status: Int,
    val success: Boolean
) {
    fun toDomain() = MonthlyHoroscope(
        monthlyHoroscopeText = data.horoscope_data,
        month = data.month,
        standoutDays = data.standout_days,
        challengingDays = data.challenging_days
    )
}

@JsonClass(generateAdapter = true)
data class MonthlyHoroscopeData(
    val challenging_days: String,
    val horoscope_data: String,
    val month: String,
    val standout_days: String
)