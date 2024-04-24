package dadm.hsingh.horoscopoapp.data.horoscope.daily

import dadm.hsingh.horoscopoapp.data.horoscope.ConnectivityChecker
import dadm.hsingh.horoscopoapp.data.horoscope.model.toDailyDomain
import dadm.hsingh.horoscopoapp.domain.model.DailyHoroscope
import dadm.hsingh.horoscopoapp.utils.NoInternetException
import javax.inject.Inject

class DailyHoroscopeRepositoryImpl @Inject constructor(
    private val dataSource: DailyHoroscopeDataSource,
    private val checker: ConnectivityChecker
) : DailyHoroscopeRepository{

    private lateinit var sign: String

    init {
        //Obtener el valor seleccionado
        sign = "leo"
    }

    override suspend fun getDailyHoroscope(sign: String): Result<DailyHoroscope> {
        if (checker.isConnectionAvailable()) {
            return dataSource.getDailyHoroscope(sign).toDailyDomain()
        } else {
            return Result.failure(NoInternetException("Internet Connection Failed"))
        }
    }

}