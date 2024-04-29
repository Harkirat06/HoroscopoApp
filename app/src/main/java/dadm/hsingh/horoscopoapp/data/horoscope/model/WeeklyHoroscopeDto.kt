package dadm.hsingh.horoscopoapp.data.horoscope.model

import com.squareup.moshi.JsonClass
import dadm.hsingh.horoscopoapp.domain.model.WeeklyHoroscope
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

@JsonClass(generateAdapter = true)
data class WeeklyHoroscopeDto(
    val data: WeeklyHoroscopeData,
    val status: Int,
    val success: Boolean
) {
    fun toDomain(): WeeklyHoroscope {
        val parts = data.week.split(" - ")
        val formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy")

        try {
            val startDate = LocalDate.parse(parts[0].trim(), formatter)
            val endDate = LocalDate.parse(parts[1].trim(), formatter)

            return WeeklyHoroscope(
                weeklyHoroscopeText = data.horoscope_data,
                week = data.week,
                startingDate = startDate,
                endDate = endDate
            )
        } catch (e: DateTimeParseException) {
            println("Error al parsear la fecha: ${e.message}")
        }

        return WeeklyHoroscope(
            weeklyHoroscopeText = data.horoscope_data,
            week = data.week,
            startingDate = LocalDate.now(),
            endDate = LocalDate.now()
        )
    }
}

@JsonClass(generateAdapter = true)
data class WeeklyHoroscopeData(
    val horoscope_data: String,
    val week: String
)