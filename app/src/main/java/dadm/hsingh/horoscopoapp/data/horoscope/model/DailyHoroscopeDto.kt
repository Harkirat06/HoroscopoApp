package dadm.hsingh.horoscopoapp.data.horoscope.model

import com.squareup.moshi.JsonClass
import dadm.hsingh.horoscopoapp.domain.model.DailyHoroscope
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

@JsonClass(generateAdapter = true)
data class DailyHoroscopeDto(
    val data : DailyHoroscopeData,
    val status: Int,
    val success: Boolean
){
    fun toDomain(): DailyHoroscope {
        try {
            val formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy")
            val date = LocalDate.parse(data.date.trim(), formatter)

            return DailyHoroscope(
                dailyHoroscopeText = data.horoscope_data,
                date = date
            )
        } catch (e: DateTimeParseException) {
            println("Error al parsear la fecha: ${e.message}")
        }
        return DailyHoroscope(
            dailyHoroscopeText = data.horoscope_data,
            date = LocalDate.now()
        )
    }
}

@JsonClass(generateAdapter = true)
data class DailyHoroscopeData(
    val date: String,
    val horoscope_data: String
)