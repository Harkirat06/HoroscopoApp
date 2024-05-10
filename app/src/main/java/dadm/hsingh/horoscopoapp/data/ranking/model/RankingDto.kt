package dadm.hsingh.horoscopoapp.data.ranking.model

data class RankingDto(
    val fecha: String,
    val items: MutableList<RankingItem> = mutableListOf()
)
