package dadm.hsingh.horoscopoapp.data.ranking

import dadm.hsingh.horoscopoapp.data.horoscope.model.DailyHoroscopeDto
import dadm.hsingh.horoscopoapp.data.ranking.model.RankingDto
import retrofit2.Response

interface RankingDataSource {

    suspend fun getRanking(): Result<RankingDto>

}