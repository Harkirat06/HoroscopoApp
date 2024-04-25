package dadm.hsingh.horoscopoapp.data.horoscope.model

import com.squareup.moshi.JsonClass
import dadm.hsingh.horoscopoapp.domain.model.MonthlyHoroscope
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.*

@JsonClass(generateAdapter = true)
data class MonthlyHoroscopeDto(
    val data: MonthlyHoroscopeData,
    val status: Int,
    val success: Boolean
) {
    fun toDomain(): MonthlyHoroscope {
        val formatter = DateTimeFormatter.ofPattern("MMMM yyyy")
        val yearMonth = YearMonth.parse(data.month, formatter)

        // Obtener el mes y el año por separado
        val month = yearMonth.monthValue
        val year = yearMonth.year

        val standoutDaysList = data.standout_days.split(",").map { it.trim().toInt() }
        val challengingDaysList = data.standout_days.split(",").map { it.trim().toInt() }

        return MonthlyHoroscope(
            monthlyHoroscopeText = data.horoscope_data,
            month = month,
            year = year,
            standoutDays = standoutDaysList.toIntArray(),
            challengingDays = challengingDaysList.toIntArray()
        )
    }
}

@JsonClass(generateAdapter = true)
data class MonthlyHoroscopeData(
    val challenging_days: String,
    val horoscope_data: String,
    val month: String,
    val standout_days: String
)