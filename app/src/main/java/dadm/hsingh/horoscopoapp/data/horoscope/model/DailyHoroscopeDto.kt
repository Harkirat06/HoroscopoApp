package dadm.hsingh.horoscopoapp.data.horoscope.model

import com.squareup.moshi.JsonClass
import dadm.hsingh.horoscopoapp.domain.model.DailyHoroscope
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@JsonClass(generateAdapter = true)
data class DailyHoroscopeDto(
    val data : DailyHoroscopeData,
    val status: Int,
    val success: Boolean
){
    fun toDomain(): DailyHoroscope {
        val formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy")
        val date = LocalDate.parse(data.date.trim(), formatter)

        return DailyHoroscope(
            dailyHoroscopeText = data.horoscope_data,
            date = date
        )
    }
}

@JsonClass(generateAdapter = true)
data class DailyHoroscopeData(
    val date: String,
    val horoscope_data: String
)