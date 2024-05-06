package dadm.hsingh.horoscopoapp.ui

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat
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
import java.util.Calendar
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

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.POST_NOTIFICATIONS),0 )
            }
        }
        val time = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, 3)
            set(Calendar.MINUTE, 3)
            set(Calendar.SECOND, 0)
        }

        val birthday = Calendar.getInstance().apply {// AQQUÍ TENGO QUE PONER EL TIEMPO DEL CUMPLEAÑOS
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, 3)
            set(Calendar.MINUTE, 3)
            set(Calendar.SECOND, 0)
        }
        val thisYear = Calendar.getInstance()
        thisYear.set(Calendar.DAY_OF_YEAR, 1)
        thisYear.set(Calendar.HOUR_OF_DAY, 0)
        thisYear.set(Calendar.MINUTE, 0)
        thisYear.set(Calendar.SECOND, 0)
        thisYear.set(Calendar.MILLISECOND, 0)
        val nextBirthday = thisYear.timeInMillis + birthday.timeInMillis

        alarmService.setReminderAlarm(time.timeInMillis)
        alarmService.setBirthdayAlarm(nextBirthday, "Paco")
        // alarmService.setInmediateAlarm()
    }

    // Soporte a la flecha hacia atrás en los Settings
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() // || super.onSupportNavigateUp()
    }

}
