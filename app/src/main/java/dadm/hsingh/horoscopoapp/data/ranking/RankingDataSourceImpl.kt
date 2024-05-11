package dadm.hsingh.horoscopoapp.data.ranking

import dadm.hsingh.horoscopoapp.data.ranking.model.RankingDto
import org.jsoup.Jsoup


class RankingDataSourceImpl : RankingDataSource {
    override suspend fun getRanking(): Result<RankingDto> {

        var document = Jsoup.connect("https://www.esperanzagraciaoficial.es/horoscopo-semanal/").get()
        var items = document.select("div.row ranking")

        val date = items.first()?.data().toString()

        TODO()
    }
}