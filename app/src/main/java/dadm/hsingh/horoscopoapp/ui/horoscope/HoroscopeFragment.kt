package dadm.hsingh.horoscopoapp.ui.horoscope

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import dadm.hsingh.horoscopoApp.R
import dadm.hsingh.horoscopoApp.databinding.FragmentHoroscopeBinding
import dadm.hsingh.horoscopoApp.databinding.FragmentProfileBinding

class HoroscopeFragment: Fragment(R.layout.fragment_horoscope) {

    private var _binding : FragmentHoroscopeBinding? = null
    private val binding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHoroscopeBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}