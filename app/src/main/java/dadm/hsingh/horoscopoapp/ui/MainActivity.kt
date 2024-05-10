package dadm.hsingh.horoscopoapp.ui

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationBarView
import dadm.hsingh.horoscopoapp.R
import dadm.hsingh.horoscopoapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jsoup.Jsoup

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var sharedPreferences: SharedPreferences


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

        //Testear
        CoroutineScope(Dispatchers.IO).launch {
            var document = Jsoup.connect("https://www.esperanzagraciaoficial.es/horoscopo-semanal/").get()
            var items = document.select("div.row.ranking")

            val html = Jsoup.parse(items.html().trimIndent())

            val fecha = html.select("div.header").text()

            Log.d("Scrapping", fecha)


            // Recorrer cada elemento del ranking
            val ranking = items.select("div.content")
            ranking.select("ul li").forEachIndexed { index, element ->
                val posicion = element.text().substringBefore(" ")
                val signo = element.text().substringAfter(" ").substringBefore(" ")
                val enlace = element.select("a").attr("href")
                val imagen = element.select("img").attr("src")

                Log.d(
                    "Scrapping",
                    "Posición: $posicion, Signo: $signo, Enlace: $enlace, Imagen: $imagen"
                )

            }
        }



    }

    // Soporte a la flecha hacia atrás en los Settings
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() // || super.onSupportNavigateUp()
    }

}
