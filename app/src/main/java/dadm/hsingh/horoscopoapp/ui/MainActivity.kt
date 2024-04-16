package dadm.hsingh.horoscopoapp.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationBarView
import dadm.hsingh.horoscopoApp.R
import dadm.hsingh.horoscopoApp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

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



    }
}