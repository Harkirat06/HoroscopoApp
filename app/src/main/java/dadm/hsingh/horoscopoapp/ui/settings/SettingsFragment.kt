package dadm.hsingh.horoscopoapp.ui.settings

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import dadm.hsingh.horoscopoapp.R
import dadm.hsingh.horoscopoapp.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment(R.layout.fragment_settings){

    private var _binding : FragmentSettingsBinding? = null
    private val binding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSettingsBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = SettingsFragment()
    }
}