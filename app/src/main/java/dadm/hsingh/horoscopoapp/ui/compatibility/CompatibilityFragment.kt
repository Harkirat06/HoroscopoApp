package dadm.hsingh.horoscopoapp.ui.compatibility

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import dadm.hsingh.horoscopoApp.R
import dadm.hsingh.horoscopoApp.databinding.FragmentCompatibilityBinding
import dadm.hsingh.horoscopoApp.databinding.FragmentProfileBinding

class CompatibilityFragment : Fragment(R.layout.fragment_compatibility){

    private var _binding : FragmentCompatibilityBinding? = null
    private val binding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCompatibilityBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}