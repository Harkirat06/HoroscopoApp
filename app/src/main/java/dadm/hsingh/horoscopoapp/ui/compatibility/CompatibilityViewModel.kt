package dadm.hsingh.horoscopoapp.ui.compatibility

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dadm.hsingh.horoscopoapp.data.friend.FriendsRepository
import dadm.hsingh.horoscopoapp.domain.calculations.CompatibilityCalculator
import dadm.hsingh.horoscopoapp.domain.calculations.getZodiacSign
import dadm.hsingh.horoscopoapp.domain.model.Friend
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
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

    val list = rep.getAllFriend().stateIn(
        scope = viewModelScope,
        initialValue = listOf(),
        started = SharingStarted.WhileSubscribed()
    )

    public fun addToFavourites(name: String, dateBirth: String, timeBirth: String, placeBirth: String){

        Log.d("HOLAAAA", "FORMAT")

        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy") // Formato del string de fecha
        val dateBirth_local: LocalDate = LocalDate.parse(dateBirth, formatter) // Parseamos el string a LocalDate

        val formatter2 = DateTimeFormatter.ofPattern("HH:mm") // Formato del string de hora
        val timeBirth_local: LocalTime = LocalTime.parse(timeBirth, formatter2) // Parseamos el string a LocalTime

        val format = SimpleDateFormat("yyyy/MM/dd") // Formato del string de fecha
        val date: Date = format.parse(dateBirth) // Parseamos el string a Date

        val friend = Friend(
            id = Random.nextInt(100).toString(),
            name = name,
            dateBirth = dateBirth_local,
            timeBirth = timeBirth_local,
            placeBirth = placeBirth,
            zodiacSign = getZodiacSign(date)
        )
        viewModelScope.launch {
            rep.addFriend(friend)
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


}

