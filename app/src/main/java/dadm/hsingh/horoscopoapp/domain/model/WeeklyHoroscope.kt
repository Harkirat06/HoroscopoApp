package dadm.hsingh.horoscopoapp.domain.model

import java.time.DayOfWeek

data class WeeklyHoroscope(
    val weeklyHoroscopeText : String,
    val week: Int
) : Horoscope(weeklyHoroscopeText)
