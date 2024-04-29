package dadm.hsingh.horoscopoapp.ui.horoscope.monthly

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
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import dadm.hsingh.horoscopoapp.R
import dadm.hsingh.horoscopoapp.databinding.FragmentMonthlyBinding
import dadm.hsingh.horoscopoapp.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint

class MonthlyFragment : Fragment(R.layout.fragment_monthly){
    private var _binding : FragmentMonthlyBinding? = null
    private val binding get() = _binding!!

    private val viewModel : MonthlyViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMonthlyBinding.bind(view)

        viewModel.getMonthlyHoroscope()

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.monthlyHoroscope.collect{monthlyHoroscope ->
                    if (monthlyHoroscope != null) {
                        binding.textViewParagraph.text = monthlyHoroscope.monthlyHoroscopeText
                    }
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch{
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.showError.collect{ show ->
                    if (show != null){
                        Snackbar.make(binding.root, show.toString(), Snackbar.LENGTH_SHORT).show()
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