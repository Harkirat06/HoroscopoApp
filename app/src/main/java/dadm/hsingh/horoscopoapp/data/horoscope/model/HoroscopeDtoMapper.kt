package dadm.hsingh.horoscopoapp.data.horoscope.model

import retrofit2.Response
import java.io.IOException

fun Response<DailyHoroscopeDto>.toDomain() =
    if (isSuccessful) Result.success((body() as DailyHoroscopeDto).toDomain())
    else Result.failure(IOException())
/*
fun Response<WeeklyHoroscopeDto>.toDomain() =
    if (isSuccessful) Result.success((body() as WeeklyHoroscopeDto).toDomain())
    else Result.failure(IOException())

fun Response<MonthlyHoroscopeDto>.toDomain() =
    if (isSuccessful) Result.success((body() as MonthlyHoroscopeDto).toDomain())
    else Result.failure(IOException())

 */