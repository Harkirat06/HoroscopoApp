package dadm.hsingh.horoscopoapp.ui.compatibility

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dadm.hsingh.horoscopoapp.data.friend.FriendsRepository
import dadm.hsingh.horoscopoapp.domain.calculations.CompatibilityCalculator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

/*
@HiltViewModel
class CompatibilityViewModel @Inject constructor(
    private val rep: FriendsRepository
    ): ViewModel(){

    val list = rep.getAllFriend().stateIn(
        scope = viewModelScope,
        initialValue = listOf(),
        started = SharingStarted.WhileSubscribed()
    )



}

 */