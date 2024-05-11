package dadm.hsingh.horoscopoapp.ui.onboarding.screens

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import dadm.hsingh.horoscopoapp.R
import dadm.hsingh.horoscopoapp.databinding.FragmentOnboardingFirstBinding
import dadm.hsingh.horoscopoapp.ui.onboarding.OnboardingViewModel
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.time.ZoneId
import java.util.Calendar
import java.util.Locale

class OnboardingFirstFragment : Fragment(R.layout.fragment_onboarding_first){
    private var _binding : FragmentOnboardingFirstBinding? = null
    private val binding get() = _binding!!

    private val viewModel: OnboardingViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentOnboardingFirstBinding.bind(view)

        val viewPager =  activity?.findViewById<ViewPager2>(R.id.viewPager)

        binding.next.setOnClickListener {
            viewPager?.currentItem = 1
            if (!binding.editTextName.text.isNullOrBlank()) {
                //Log.d("Setname", binding.editTextName.text.toString())
                viewModel.setName(binding.editTextName.text.toString())
                //viewModel.getName()?.let { name -> Log.d("Setname in Viewmodel", name) }
            }
        }

        //DatePicker
        val datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select date")
                .build()

        binding.datePicker.setOnClickListener {
            datePicker.show(childFragmentManager, "tag")
        }

        datePicker.addOnPositiveButtonClickListener { selectedDate ->
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = selectedDate
            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val selectedDateString = dateFormat.format(calendar.time)
            binding.birthDateInput.text = Editable.Factory.getInstance().newEditable(selectedDateString)
            viewModel.setBirthDate(
                calendar.time
                    .toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate()
            )
            //viewModel.getBirthDate()?.let { Log.d("birthDate sett", it.toString()) }
        }

        //TimePicker
        val timePicker =
            MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_12H)
                .setHour(12)
                .setMinute(10)
                .setTitleText("Select time")
                .build()

        binding.timePicker.setOnClickListener {
            timePicker.show(childFragmentManager, "")
        }

        timePicker.addOnPositiveButtonClickListener {
            val hour = timePicker.hour
            val minute = timePicker.minute
            val formattedTime = String.format(Locale.getDefault(), "%02d:%02d", hour, minute)
            binding.birthTimeInput.text = Editable.Factory.getInstance().newEditable(formattedTime)
            viewModel.setBirthTime(LocalTime.of(timePicker.hour, timePicker.minute))
            //viewModel.getBirthTime()?.let { Log.d("birthDate sett", it.toString()) }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}