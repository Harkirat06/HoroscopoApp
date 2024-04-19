package dadm.hsingh.horoscopoapp.ui

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.viewpager.widget.ViewPager
import com.google.android.material.navigation.NavigationBarView
import dadm.hsingh.horoscopoapp.R
import dadm.hsingh.horoscopoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        navController = binding.NavHost.getFragment<NavHostFragment>().navController
        val navigationBarView = binding.bottomNavigationView as NavigationBarView
        navigationBarView.setupWithNavController(navController)
        val appBar = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBar)

        sharedPreferences = getSharedPreferences("onBoarding", MODE_PRIVATE)

        // Comprueba si es la primera vez que se ejecuta la aplicación
        val isFirstRun = sharedPreferences.getBoolean("isFirstRun", true)

        if (isFirstRun) {
            val intent = Intent(this, OnBoardingActivity::class.java)
            startActivity(intent)
        }
    }
}
