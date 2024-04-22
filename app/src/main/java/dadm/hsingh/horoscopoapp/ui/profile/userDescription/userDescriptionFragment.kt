package dadm.hsingh.horoscopoapp.ui.profile.userDescription

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import dadm.hsingh.horoscopoapp.R
import dadm.hsingh.horoscopoapp.databinding.FragmentDescriptionBinding
import dadm.hsingh.horoscopoapp.databinding.FragmentProfileBinding
import dadm.hsingh.horoscopoapp.databinding.FragmentUserInfoBinding

class userDescriptionFragment : Fragment(R.layout.fragment_description){

    private var _binding : FragmentDescriptionBinding? = null
    private val binding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDescriptionBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}