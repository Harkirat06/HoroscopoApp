package dadm.hsingh.horoscopoapp.ui.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dadm.hsingh.horoscopoapp.R
import dadm.hsingh.horoscopoapp.databinding.FragmentProfileBinding
import dadm.hsingh.horoscopoapp.ui.compatibility.TabPagerAdapter2

class ProfileFragment : Fragment(R.layout.fragment_profile){

    private var _binding : FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val tabTitles = arrayListOf("Personal Info", "Astral Description")



    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentProfileBinding.bind(view)

        val adapter = TabPagerAdapter3(this)

        binding.viewPagerProfile.adapter = adapter


        TabLayoutMediator(binding.tabLayoutProfile, binding.viewPagerProfile) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()



        binding.tabLayoutProfile.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(p0: TabLayout.Tab?) {
                if (p0 != null){
                    binding.viewPagerProfile.currentItem = p0.position
                }
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {
            }

            override fun onTabReselected(p0: TabLayout.Tab?) {
            }

        })

        binding.viewPagerProfile.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.tabLayoutProfile.selectTab((binding.tabLayoutProfile.getTabAt((position))))
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}