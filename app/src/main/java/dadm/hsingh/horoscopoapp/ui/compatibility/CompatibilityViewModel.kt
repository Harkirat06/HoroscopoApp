package dadm.hsingh.horoscopoapp.ui.compatibility

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dadm.hsingh.horoscopoapp.data.friend.FriendsRepository
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
                    friends
                } else {
                    friends.filter { friend ->
                        friend.name.contains(query, ignoreCase = true)
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

    public fun setFirstFriend(name : String){
        val temp = allFriends.value.first {
            it.name == name
        }
        _firstFriend.value = temp
    }

    public fun setSecondFriend(name : String){
        val temp = allFriends.value.first {
            it.name == name
        }
        _secondFriend.value = temp
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
}

