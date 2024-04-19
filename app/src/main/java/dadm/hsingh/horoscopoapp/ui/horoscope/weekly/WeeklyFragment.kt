package dadm.hsingh.horoscopoapp.ui.horoscope.weekly

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import dadm.hsingh.horoscopoapp.R
import dadm.hsingh.horoscopoapp.databinding.FragmentProfileBinding
import dadm.hsingh.horoscopoapp.databinding.FragmentWeeklyBinding

class WeeklyFragment : Fragment(R.layout.fragment_weekly){
    private var _binding : FragmentWeeklyBinding? = null
    private val binding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentWeeklyBinding.bind(view)

        Log.d("FRagment", "Holaaa3")

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}