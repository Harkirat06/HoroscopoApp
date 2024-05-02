package dadm.hsingh.horoscopoapp.ui.compatibility.friends.formFriends

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import dadm.hsingh.horoscopoapp.R
import dadm.hsingh.horoscopoapp.databinding.FormsFriendBinding
import dadm.hsingh.horoscopoapp.domain.model.Friend
import dadm.hsingh.horoscopoapp.ui.compatibility.CompatibilityViewModel
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale

class FriendFormFragment() : DialogFragment(R.layout.forms_friend){

    private var _binding: FormsFriendBinding? = null
    private  val binding
        get() = _binding!!


    private val viewModel: CompatibilityViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FormsFriendBinding.bind(view)

        binding.buttonCancel.setOnClickListener {
            viewModel.resetFriend()
            this.dismiss()
        }

        if(viewModel.friend.value != null){
            val friend : Friend = viewModel.friend.value!!
            val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
            binding.editTextName.text = Editable.Factory.getInstance().newEditable(friend.name)
            binding.birthDateInput.text = Editable.Factory.getInstance().newEditable(friend.dateBirth.format(formatter).toString())
            binding.birthTimeInput.text = Editable.Factory.getInstance().newEditable(friend.timeBirth.toString())
            binding.editTextPlaceBirth.text = Editable.Factory.getInstance().newEditable(friend.placeBirth)
        }

        //DatePicker
        val datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select date")
                .build()

        binding.datePicker.setOnClickListener {
            datePicker.show(childFragmentManager, "tag");
        }

        datePicker.addOnPositiveButtonClickListener { selectedDate ->
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = selectedDate
            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val selectedDateString = dateFormat.format(calendar.time)
            binding.birthDateInput.text = Editable.Factory.getInstance().newEditable(selectedDateString)
        }

        //TimePicker
        val TimePicker =
            MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_12H)
                .setHour(12)
                .setMinute(10)
                .setTitleText("Select Appointment time")
                .build()

        binding.timePicker.setOnClickListener {
            TimePicker.show(childFragmentManager, "")
        }

        TimePicker.addOnPositiveButtonClickListener {
            val hour = TimePicker.hour
            val minute = TimePicker.minute
            val formattedTime = String.format(Locale.getDefault(), "%02d:%02d", hour, minute)
            binding.birthTimeInput.text = Editable.Factory.getInstance().newEditable(formattedTime)
        }



        binding.buttonAddFriend.setOnClickListener {
            viewModel.addToFavourites(
                binding.editTextName.text.toString(),
                binding.birthDateInput.text.toString(),
                binding.birthTimeInput.text.toString(),
                binding.editTextPlaceBirth.text.toString()
            )
            viewModel.resetFriend()
            this.dismiss()
        }

    }

    override fun onStart() {
        super.onStart()
        // Cambiar el tamaño del diálogo
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,  // Ancho deseado, por ejemplo: ViewGroup.LayoutParams.MATCH_PARENT para que ocupe todo el ancho
            ViewGroup.LayoutParams.WRAP_CONTENT   // Altura deseada, por ejemplo: ViewGroup.LayoutParams.WRAP_CONTENT para ajustarse al contenido
        )
        val transparentDrawable = ColorDrawable(Color.TRANSPARENT)
        dialog?.window?.setBackgroundDrawable(transparentDrawable)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}