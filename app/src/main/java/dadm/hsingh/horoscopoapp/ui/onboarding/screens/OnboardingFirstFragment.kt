package dadm.hsingh.horoscopoapp.ui.onboarding.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import dadm.hsingh.horoscopoapp.R
import dadm.hsingh.horoscopoapp.databinding.FragmentOnboardingFirstBinding

class OnboardingFirstFragment : Fragment(R.layout.fragment_onboarding_first){
    private var _binding : FragmentOnboardingFirstBinding? = null
    private val binding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentOnboardingFirstBinding.bind(view)

        val viewPager =  activity?.findViewById<ViewPager2>(R.id.viewPager)

        binding.next.setOnClickListener {
            viewPager?.currentItem = 1
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}