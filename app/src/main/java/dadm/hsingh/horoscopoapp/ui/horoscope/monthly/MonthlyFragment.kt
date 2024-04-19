package dadm.hsingh.horoscopoapp.ui.horoscope.monthly

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import dadm.hsingh.horoscopoapp.R
import dadm.hsingh.horoscopoapp.databinding.FragmentMonthlyBinding
import dadm.hsingh.horoscopoapp.databinding.FragmentProfileBinding

class MonthlyFragment : Fragment(R.layout.fragment_monthly){
    private var _binding : FragmentMonthlyBinding? = null
    private val binding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMonthlyBinding.bind(view)

        Log.d("FRagment", "Holaaa2")

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}