package dadm.hsingh.horoscopoapp.ui.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import dadm.hsingh.horoscopoApp.R
import dadm.hsingh.horoscopoApp.databinding.FragmentProfileBinding

class ProfileFragment : Fragment(R.layout.fragment_profile){

    private var _binding : FragmentProfileBinding? = null
    private val binding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentProfileBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}