package dadm.hsingh.horoscopoapp.data.horoscope.model

import com.squareup.moshi.JsonClass
import dadm.hsingh.horoscopoapp.domain.model.DailyHoroscope

@JsonClass(generateAdapter = true)
data class DailyHoroscopeDto(
    val data : HoroscopeData,
    val status: Int,
    val success: Boolean
){
    fun toDomain() = DailyHoroscope(dailyHoroscopeText = data.horoscopeData, date = data.date)
}

data class HoroscopeData(
    val date: String,
    val horoscopeData: String
)