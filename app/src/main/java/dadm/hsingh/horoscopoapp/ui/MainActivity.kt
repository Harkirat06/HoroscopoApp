package dadm.hsingh.horoscopoapp.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationBarView
import dadm.hsingh.horoscopoapp.R
import dadm.hsingh.horoscopoapp.databinding.ActivityMainBinding
import dadm.hsingh.horoscopoapp.ui.settings.SettingsViewModel
import dadm.hsingh.horoscopoapp.utils.AlarmService
import dadm.hsingh.horoscopoapp.utils.createNotificationChannel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var sharedPreferences: SharedPreferences
    private val settingsViewModel : SettingsViewModel by  viewModels()
    lateinit var alarmService: AlarmService


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        setContentView(binding.root)
        binding.toolbar.setTitleTextAppearance(this, R.style.ToolbarTitleText);
        setSupportActionBar(binding.toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        navController = binding.NavHost.getFragment<NavHostFragment>().navController
        val navigationBarView = binding.bottomNavigationView as NavigationBarView
        navigationBarView.setupWithNavController(navController)
        val appBar = AppBarConfiguration(setOf(
            R.id.profileFragment,
            R.id.horoscopeFragment,
            R.id.compatibilityFragment,
            // R.id.settingsFragment (lo excluimos para que aparezca tenga flecha hacia atrás)
        ))
        setupActionBarWithNavController(navController, appBar)

        sharedPreferences = getSharedPreferences("onBoarding", MODE_PRIVATE)

        // Comprueba si es la primera vez que se ejecuta la aplicación
        val isFirstRun = sharedPreferences.getBoolean("isFirstRun", true)

        if (isFirstRun) {
            val intent = Intent(this, OnBoardingActivity::class.java)
            startActivity(intent)
        }

        // DarkMode. Hemos de activarlo aquí si queremos que se vea al iniciar la app
        lifecycleScope.launch {
            settingsViewModel.darkMode.collect { isDarkModeEnabled ->
                if (isDarkModeEnabled) {
                    // Cambiar al tema oscuro
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                } else {
                    // Cambiar al tema claro
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
            }
        }

        // Crear canal de notificación
        createNotificationChannel(this)
        alarmService = AlarmService(this)
        alarmService.setRepetitiveAlarm(TimeUnit.HOURS.toMillis(1) + TimeUnit.MINUTES.toMillis(8))
    }

    // Soporte a la flecha hacia atrás en los Settings
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() // || super.onSupportNavigateUp()
    }

}
