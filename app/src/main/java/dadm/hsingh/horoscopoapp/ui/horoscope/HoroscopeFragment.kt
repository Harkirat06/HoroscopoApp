package dadm.hsingh.horoscopoapp.ui.horoscope

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dadm.hsingh.horoscopoapp.R
import dadm.hsingh.horoscopoapp.databinding.FragmentHoroscopeBinding
import dadm.hsingh.horoscopoapp.ui.horoscope.daily.DailyFragment
import dadm.hsingh.horoscopoapp.ui.horoscope.monthly.MonthlyFragment
import dadm.hsingh.horoscopoapp.ui.horoscope.weekly.WeeklyFragment
import dadm.hsingh.horoscopoapp.ui.onboarding.ViewPagerAdapter

class HoroscopeFragment: Fragment(R.layout.fragment_horoscope) {

    private var _binding : FragmentHoroscopeBinding? = null
    private val binding get() = _binding!!

    private val tabTitles = arrayListOf("Daily", "Weekly", "Monthly")

/*
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHoroscopeBinding.inflate(layoutInflater)
        setUpTabLayoutWithViewPager()
        return binding.root

    }

    private fun setUpTabLayoutWithViewPager() {
        binding.viewPager2.adapter = TabPagerAdapter(this)
        TabLayoutMediator(binding.tabLayout, binding.viewPager2){
            tab, position -> tab.text = tabTitles[position]

        }.attach()
    }

 */



    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHoroscopeBinding.bind(view)


        val adapter = TabPagerAdapter(this)

        binding.viewPager2.adapter = adapter


        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()



        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(p0: TabLayout.Tab?) {
                if (p0 != null){
                    binding.viewPager2.currentItem = p0.position
                }
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {
            }

            override fun onTabReselected(p0: TabLayout.Tab?) {
            }

        })

        binding.viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.tabLayout.selectTab((binding.tabLayout.getTabAt((position))))
            }
        })


    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}