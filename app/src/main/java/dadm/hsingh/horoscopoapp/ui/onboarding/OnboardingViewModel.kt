package dadm.hsingh.horoscopoapp.ui.onboarding

import android.util.Log
import androidx.lifecycle.ViewModel
import dadm.hsingh.horoscopoapp.domain.model.Friend
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import java.time.LocalDate
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(): ViewModel() {
    private val _name = MutableStateFlow<String?>(null)
    val name = _name.asStateFlow()

    private val _birthDate = MutableStateFlow<LocalDate?>(null)
    val birthDate = _birthDate.asStateFlow()

    private val _birthTime = MutableStateFlow<LocalTime?>(null)
    val birthTime = _birthTime.asStateFlow()

    private val _birthPlace = MutableStateFlow<String?>(null)
    val birthPlace = _birthPlace.asStateFlow()

    private val _image = MutableStateFlow<String?>(null)
    val image = _image.asStateFlow()

    fun setName(name: String) {
        _name.value = name
    }

    fun setBirthDate(birthDate: LocalDate) {
        _birthDate.value = birthDate
    }

    fun setBirthTime(birthTime: LocalTime) {
        _birthTime.value = birthTime
    }

    fun setBirthPlace(birthPlace: String) {
        _birthPlace.value = birthPlace
    }
    fun setImageUri(uri : String){
        _image.value = uri
    }

    fun getName(): String? {
        return _name.value
    }

    fun getBirthDate(): LocalDate? {
        return _birthDate.value
    }

    fun getBirthTime(): LocalTime? {
        return _birthTime.value
    }

    fun getBirthPlace(): String? {
        return _birthPlace.value
    }
    fun getImageUri(): String? {
        return _image.value
    }


    fun checkRequiredFields(): Boolean {
        Log.d("check value birthDate", _birthDate.value.toString())
        return  _birthDate.value.toString() != null &&
                !_name.value.toString().isNullOrBlank() &&
                !_birthPlace.value.toString().isNullOrBlank()
    }
}