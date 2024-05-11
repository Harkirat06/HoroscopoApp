package dadm.hsingh.horoscopoapp.ui.horoscope.monthly

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.snackbar.Snackbar
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions
import dadm.hsingh.horoscopoapp.R
import dadm.hsingh.horoscopoapp.databinding.FragmentMonthlyBinding
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
        val options = TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.ENGLISH)
            .setTargetLanguage(TranslateLanguage.SPANISH)
            .build()

        val englishSpanishTranslator = Translation.getClient(options)
        lifecycle.addObserver(englishSpanishTranslator)

        viewModel.getMonthlyHoroscope()

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.monthlyHoroscope.collect{monthlyHoroscope ->
                    if (monthlyHoroscope != null) {
                        viewModel.getLanguage()
                        viewModel.language.collect { language ->
                            if (language.isNotEmpty()) {
                                if (language == "es") {
                                    englishSpanishTranslator.translate(monthlyHoroscope.monthlyHoroscopeText)
                                        .addOnSuccessListener { translatedText ->
                                            // Translation successful.
                                            binding.textViewParagraph.text = translatedText
                                        }
                                        .addOnFailureListener { exception ->
                                            // Error.
                                            // ...
                                        }
                                } else {
                                    binding.textViewParagraph.text =
                                        monthlyHoroscope.monthlyHoroscopeText
                                }
                            }
                        }
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