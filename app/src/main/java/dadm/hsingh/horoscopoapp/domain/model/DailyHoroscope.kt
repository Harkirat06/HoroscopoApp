package dadm.hsingh.horoscopoapp.domain.model


data class DailyHoroscope(
    val dailyHoroscopeText : String,
    val date: String,
) : Horoscope(dailyHoroscopeText)
