package dadm.hsingh.horoscopoapp.ui.profile.userInfo

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import dadm.hsingh.horoscopoapp.R
import dadm.hsingh.horoscopoapp.databinding.FragmentProfileBinding
import dadm.hsingh.horoscopoapp.databinding.FragmentUserInfoBinding

class userInfoFragment : Fragment(R.layout.fragment_user_info){

    private var _binding : FragmentUserInfoBinding? = null
    private val binding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentUserInfoBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}