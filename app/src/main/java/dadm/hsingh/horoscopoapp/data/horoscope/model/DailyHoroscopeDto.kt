package dadm.hsingh.horoscopoapp.data.horoscope.model

import com.squareup.moshi.JsonClass
import dadm.hsingh.horoscopoapp.domain.model.DailyHoroscope

@JsonClass(generateAdapter = true)
data class DailyHoroscopeDto(
    val data : DailyHoroscopeData,
    val status: Int,
    val success: Boolean
){
    fun toDomain() = DailyHoroscope(dailyHoroscopeText = data.horoscope_data, date = data.date)
}

@JsonClass(generateAdapter = true)
data class DailyHoroscopeData(
    val date: String,
    val horoscope_data: String
)