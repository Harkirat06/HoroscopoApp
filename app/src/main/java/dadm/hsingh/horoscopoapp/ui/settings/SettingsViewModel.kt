package dadm.hsingh.horoscopoapp.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dadm.hsingh.horoscopoapp.data.friend.FriendsRepository
import dadm.hsingh.horoscopoapp.data.settings.SettingsRepository
import dadm.hsingh.horoscopoapp.domain.model.Friend
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject() constructor(
    settingsRepo: SettingsRepository,
    private val friendsRepo: FriendsRepository
) : ViewModel()   {

    val darkMode = settingsRepo.getDarkMode().stateIn(
        scope = viewModelScope,
        initialValue = false,
        started = SharingStarted.WhileSubscribed()
    )

    val notificationReminder = settingsRepo.getNotificationHoroscope().stateIn(
        scope = viewModelScope,
        initialValue = false,
        started = SharingStarted.WhileSubscribed()
    )

    val notificationBirthdays = settingsRepo.getNotificationBirthdays().stateIn(
        scope = viewModelScope,
        initialValue = false,
        started = SharingStarted.WhileSubscribed()
    )

    fun getFriendUser() : Friend {
        val friendUserFlow = friendsRepo.getFriendById("USUARIO")
        var friendUser : Friend? = null
        runBlocking {
            friendUser = friendUserFlow.first()
        }
        return friendUser!!
    }


}