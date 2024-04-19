package dadm.hsingh.horoscopoapp.ui.horoscope.daily

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import dadm.hsingh.horoscopoapp.R
import dadm.hsingh.horoscopoapp.databinding.FragmentDailyBinding
import dadm.hsingh.horoscopoapp.databinding.FragmentProfileBinding

class DailyFragment : Fragment(R.layout.fragment_daily){
    private var _binding : FragmentDailyBinding? = null
    private val binding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDailyBinding.bind(view)

        Log.d("FRagment", "Holaaa1")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}