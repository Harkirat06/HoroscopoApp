package dadm.hsingh.horoscopoapp.ui.onboarding.screens

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2
import dadm.hsingh.horoscopoapp.R
import dadm.hsingh.horoscopoapp.databinding.FragmentOnboardingSecondBinding
import dadm.hsingh.horoscopoapp.ui.onboarding.OnboardingViewModel

class OnboardingSecondFragment : Fragment(R.layout.fragment_onboarding_second){
    private var _binding : FragmentOnboardingSecondBinding? = null
    private val binding get() = _binding!!

    private val viewModel: OnboardingViewModel by activityViewModels()

    private val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()){ uri->
        if(uri!=null){
            val flag = Intent.FLAG_GRANT_READ_URI_PERMISSION
            context?.contentResolver?.takePersistableUriPermission(uri, flag)
            viewModel.setImageUri(uri.toString())
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentOnboardingSecondBinding.bind(view)
        val viewPager =  activity?.findViewById<ViewPager2>(R.id.viewPager)

        binding.finish.setOnClickListener {
            if (!binding.editTextPlaceBirth.text.isNullOrBlank()) {
                viewModel.setBirthPlace(binding.editTextPlaceBirth.text.toString())
                viewModel.getBirthPlace()?.let { place -> Log.d("Set placebirth", place) }
            }
            onBoardingFinished()
        }

        binding.previous.setOnClickListener {
            viewPager?.currentItem = 0
        }

        binding.uploadBt.setOnClickListener{
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
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