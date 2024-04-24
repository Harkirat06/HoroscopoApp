package dadm.hsingh.horoscopoapp.ui.horoscope.monthly

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import dadm.hsingh.horoscopoapp.R
import dadm.hsingh.horoscopoapp.databinding.FragmentMonthlyBinding
import dadm.hsingh.horoscopoapp.databinding.FragmentProfileBinding

class MonthlyFragment : Fragment(R.layout.fragment_monthly){
    private var _binding : FragmentMonthlyBinding? = null
    private val binding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMonthlyBinding.bind(view)


        //val frame1 = requireActivity().findViewById<FrameLayout>(R.id.frame1)

        /*
        binding.scroll3.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            val maxScroll = 500 // El valor máximo de desplazamiento para la escala
            if (scrollY > 0) {
                // Si el scroll es hacia abajo, calcula la escala basada en el desplazamiento
                frame1.isVisible = false

            } else {
                // Si el scroll es hacia arriba, calcula la escala basada en el desplazamiento
                frame1.isVisible = true
            }
        }

         */

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}