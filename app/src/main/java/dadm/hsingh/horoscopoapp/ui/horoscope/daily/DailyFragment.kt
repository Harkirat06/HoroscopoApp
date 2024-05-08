package dadm.hsingh.horoscopoapp.ui.horoscope.daily

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.snackbar.Snackbar
import dadm.hsingh.horoscopoapp.R
import dadm.hsingh.horoscopoapp.databinding.FragmentDailyBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DailyFragment : Fragment(R.layout.fragment_daily){
    private var _binding : FragmentDailyBinding? = null
    private val binding get() = _binding!!

    private val viewModel : DailyViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDailyBinding.bind(view)

        viewModel.getDailyHoroscope()

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.dailyHoroscope.collect{dailyHoroscope ->
                    if (dailyHoroscope != null) {
                        binding.textViewParagraph.text = dailyHoroscope.dailyHoroscopeText
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

        //val frame1 = requireActivity().findViewById<FrameLayout>(R.id.frame1)

        /*
        binding.scroll1.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            val maxScroll = 500 // El valor máximo de desplazamiento para la escala
            if (scrollY > 0) {
                // Si el scroll es hacia abajo, calcula la escala basada en el desplazamiento
                frame1.isVisible = false

            } else {
                // Si el scroll es hacia arriba, calcula la escala basada en el desplazamiento
                frame1.isVisible = true
            }
        }

         */
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}