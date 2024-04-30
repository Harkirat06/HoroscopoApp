package dadm.hsingh.horoscopoapp.domain.calculations

import android.util.Log
import dadm.hsingh.horoscopoapp.R
import java.util.Calendar
import java.util.Date

object SignosZodiacales {
    val ACUARIO = R.drawable.aquarius
    val PISCIS = R.drawable.pisces
    val ARIES = R.drawable.aries
    val TAURO = R.drawable.taurus
    val GEMINIS = R.drawable.gemini
    val CANCER = R.drawable.cancer
    val LEO = R.drawable.leo
    val VIRGO = R.drawable.virgo
    val LIBRA = R.drawable.libra
    val ESCORPIO = R.drawable.scorpio
    val SAGITARIO = R.drawable.sagittarius
    val CAPRICORNIO = R.drawable.capricorn
}

fun getZodiacSign(birthDate: Date): Int {
    val calendar = Calendar.getInstance()
    calendar.time = birthDate
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    Log.d(month.toString(), "debar")
    Log.d(day.toString(), "debar")

    return when {
        (month == Calendar.JANUARY && day >= 20) || (month == Calendar.FEBRUARY && day <= 18) -> SignosZodiacales.ACUARIO
        (month == Calendar.FEBRUARY && day >= 19) || (month == Calendar.MARCH && day <= 20) -> SignosZodiacales.PISCIS
        (month == Calendar.MARCH && day >= 21) || (month == Calendar.APRIL && day <= 19) -> SignosZodiacales.ARIES
        (month == Calendar.APRIL && day >= 20) || (month == Calendar.MAY && day <= 20) -> SignosZodiacales.TAURO
        (month == Calendar.MAY && day >= 21) || (month == Calendar.JUNE && day <= 20) -> SignosZodiacales.GEMINIS
        (month == Calendar.JUNE && day >= 21) || (month == Calendar.JULY && day <= 22) -> SignosZodiacales.CANCER
        (month == Calendar.JULY && day >= 23) || (month == Calendar.AUGUST && day <= 22) -> SignosZodiacales.LEO
        (month == Calendar.AUGUST && day >= 23) || (month == Calendar.SEPTEMBER && day <= 22) -> SignosZodiacales.VIRGO
        (month == Calendar.SEPTEMBER && day >= 23) || (month == Calendar.OCTOBER && day <= 22) -> SignosZodiacales.LIBRA
        (month == Calendar.OCTOBER && day >= 23) || (month == Calendar.NOVEMBER && day <= 21) -> SignosZodiacales.ESCORPIO
        (month == Calendar.NOVEMBER && day >= 22) || (month == Calendar.DECEMBER && day <= 21) -> SignosZodiacales.SAGITARIO
        else -> SignosZodiacales.CAPRICORNIO
    }
}
