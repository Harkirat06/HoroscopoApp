package dadm.hsingh.horoscopoapp.ui.onboarding.screens

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import dadm.hsingh.horoscopoapp.R
import dadm.hsingh.horoscopoapp.databinding.FragmentOnboardingSecondBinding

class OnboardingSecondFragment : Fragment(R.layout.fragment_onboarding_second){
    private var _binding : FragmentOnboardingSecondBinding? = null
    private val binding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentOnboardingSecondBinding.bind(view)
        val viewPager =  activity?.findViewById<ViewPager2>(R.id.viewPager)

        binding.finish.setOnClickListener {
            onBoardingFinished()
        }

        binding.previous.setOnClickListener {
            viewPager?.currentItem = 0
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun onBoardingFinished(){
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("isFirstRun", false)
        editor.apply()
        activity?.finish()
    }
}