package dadm.hsingh.horoscopoapp.domain.model


data class DailyHoroscope(
    val dailyHoroscopeText : String,
    val dayOfWeek: Int,
) : Horoscope(dailyHoroscopeText)
