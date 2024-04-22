package dadm.hsingh.horoscopoapp.domain.model

data class MonthlyHoroscope(
    val monthlyHoroscopeText : String,
    val month: Int
) : Horoscope(monthlyHoroscopeText)
