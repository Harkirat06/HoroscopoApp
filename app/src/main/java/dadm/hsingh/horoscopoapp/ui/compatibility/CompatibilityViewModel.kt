package dadm.hsingh.horoscopoapp.ui.compatibility

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dadm.hsingh.horoscopoapp.data.friend.FriendsRepository
import dadm.hsingh.horoscopoapp.domain.model.Compatibility
import dadm.hsingh.horoscopoapp.utils.getZodiacSign
import dadm.hsingh.horoscopoapp.utils.getZodiacSignImage
import dadm.hsingh.horoscopoapp.domain.model.Friend
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Date
import javax.inject.Inject
import kotlin.random.Random


@HiltViewModel
class CompatibilityViewModel @Inject constructor(
    private val rep: FriendsRepository
    ): ViewModel(){

    private val _friend = MutableStateFlow<Friend?>(null)
    val friend = _friend.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    private val searchQuery = _searchQuery.asStateFlow()

    private val _image = MutableStateFlow<String?>(null)
    val image = _image.asStateFlow()

    val allFriends = rep.getAllFriend().stateIn(
        scope = viewModelScope,
        initialValue = listOf(),
        started = SharingStarted.WhileSubscribed()
    )

    private val _firstFriend = MutableStateFlow<Friend?>(null)
    val firstFriend = _firstFriend.asStateFlow()

    private val _secondFriend = MutableStateFlow<Friend?>(null)
    val secondFriend = _secondFriend.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    val filteredFriends = searchQuery
        .flatMapLatest { query ->
            rep.getAllFriend().map { friends ->
                if (query.isEmpty()) {
                    friends.filter { friend ->
                        friend.id != "USUARIO"
                    }
                } else {
                    friends.filter { friend ->
                        friend.id != "USUARIO" && friend.name.contains(query, ignoreCase = true)
                    }
                }
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = listOf()
        )


    public fun getFriendNames(): List<String>{
        val listFriends = allFriends.value.map {
            it.name
        }
        return listFriends
    }

    public fun setFirstFriend(friend : Friend){
        _firstFriend.value = friend
    }

    public fun setSecondFriend(friend : Friend){
        _secondFriend.value = friend
    }

    public fun addToFavourites(name: String, dateBirth: String, timeBirth: String, placeBirth: String, uri : String?){

        Log.d("HOLAAAA", "FORMAT")

        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy") // Formato del string de fecha
        val dateBirth_local: LocalDate = LocalDate.parse(dateBirth, formatter) // Parseamos el string a LocalDate

        val formatter2 = DateTimeFormatter.ofPattern("HH:mm") // Formato del string de hora
        val timeBirth_local: LocalTime = LocalTime.parse(timeBirth, formatter2) // Parseamos el string a LocalTime

        val format = SimpleDateFormat("dd/MM/yyyy") // Formato del string de fecha
        val date: Date = format.parse(dateBirth) // Parseamos el string a Date

        if(friend.value != null){
            val newFriend = _friend.value?.let {
                Friend(
                    id = it.id,
                    name = name,
                    dateBirth = dateBirth_local,
                    timeBirth = timeBirth_local,
                    placeBirth = placeBirth,
                    defaultImage = getZodiacSignImage(date),
                    zodiacSign = getZodiacSign(date),
                    imageUri = uri
                )
            }
            viewModelScope.launch {
                if (newFriend != null) {
                    rep.updateFriend(newFriend)
                }
            }

        }else{
            val friend = Friend(
                id = Random.nextInt(1000000000).toString(),
                name = name,
                dateBirth = dateBirth_local,
                timeBirth = timeBirth_local,
                placeBirth = placeBirth,
                defaultImage = getZodiacSignImage(date),
                zodiacSign = getZodiacSign(date),
                imageUri = uri
            )
            viewModelScope.launch {
                rep.addFriend(friend)
            }
        }
    }

    public fun removeFriend(friend : Friend){
        viewModelScope.launch {
            rep.deleteFriend(friend)
        }
    }

    public fun setFriend(friend: Friend){
        _friend.update {
            friend
        }
    }
    public fun resetFriend(){
        _friend.update { null }
    }
    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun setImageUri(uri : String){
        _image.value = uri
    }
    fun resetImageUri(){
        _image.value = null
    }

    fun calculateCompatibility(sign1: String, sign2: String): Compatibility {
        val compatibilityProbability = when {
            // Ejemplos de combinaciones con mayor compatibilidad
            (sign1 == "Aries" && sign2 == "Leo") || (sign1 == "Leo" && sign2 == "Aries") -> 0.8
            (sign1 == "Aries" && sign2 == "Sagitario") || (sign1 == "Sagitario" && sign2 == "Aries") -> 0.7
            (sign1 == "Leo" && sign2 == "Sagitario") || (sign1 == "Sagitario" && sign2 == "Leo") -> 0.75
            (sign1 == "Géminis" && sign2 == "Libra") || (sign1 == "Libra" && sign2 == "Géminis") -> 0.6
            // Ejemplos de combinaciones con compatibilidad moderada
            (sign1 == "Tauro" && sign2 == "Virgo") || (sign1 == "Virgo" && sign2 == "Tauro") -> 0.5
            (sign1 == "Géminis" && sign2 == "Acuario") || (sign1 == "Acuario" && sign2 == "Géminis") -> 0.45
            // Ejemplos de combinaciones con menor compatibilidad
            (sign1 == "Cáncer" && sign2 == "Aries") || (sign1 == "Aries" && sign2 == "Cáncer") -> 0.3
            (sign1 == "Escorpio" && sign2 == "Leo") || (sign1 == "Leo" && sign2 == "Escorpio") -> 0.25
            // Combinaciones con compatibilidad baja
            (sign1 == "Piscis" && sign2 == "Géminis") || (sign1 == "Géminis" && sign2 == "Piscis") -> 0.2
            (sign1 == "Capricornio" && sign2 == "Aries") || (sign1 == "Aries" && sign2 == "Capricornio") -> 0.15
            // Por defecto, para combinaciones no especificadas
            else -> 0.4
        }

        // Generar un porcentaje de compatibilidad en base a la probabilidad
        val compatibilityPercentage = ((compatibilityProbability * 100).toInt()..100).random()

        val explanation = when (compatibilityPercentage) {
            in 80..100 -> "¡Una combinación perfecta! Ambos tienen una gran afinidad y comparten intereses similares."
            in 70..79 -> "Una combinación prometedora, con el potencial de una relación estable y satisfactoria."
            in 50..69 -> "La compatibilidad puede variar, con posibles desafíos que podrían superarse con esfuerzo y comprensión mutua."
            else -> "La compatibilidad puede ser baja y pueden surgir desafíos en la relación."
        }

        return Compatibility(compatibilityPercentage, explanation)
    }
}

