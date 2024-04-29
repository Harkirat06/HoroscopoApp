package dadm.hsingh.horoscopoapp.ui.horoscope.weekly

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import dadm.hsingh.horoscopoapp.R
import dadm.hsingh.horoscopoapp.databinding.FragmentProfileBinding
import dadm.hsingh.horoscopoapp.databinding.FragmentWeeklyBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint

class WeeklyFragment : Fragment(R.layout.fragment_weekly){
    private var _binding : FragmentWeeklyBinding? = null
    private val binding get() = _binding!!

    private val viewModel : WeeklyViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentWeeklyBinding.bind(view)

        viewModel.getWeeklyHoroscope()

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.weeklyHoroscope.collect{weeklyHoroscope ->
                    if (weeklyHoroscope != null) {
                        binding.textViewParagraph.text = weeklyHoroscope.weeklyHoroscopeText
                    }
                }
            }
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}