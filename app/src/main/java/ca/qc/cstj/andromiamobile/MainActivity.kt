package ca.qc.cstj.andromiamobile

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import ca.qc.cstj.andromiamobile.helpers.AlertDialogBuilder
import ca.qc.cstj.andromiamobile.helpers.RepositoryResult
import ca.qc.cstj.andromiamobile.helpers.Services
import ca.qc.cstj.andromiamobile.models.Exploration
import ca.qc.cstj.andromiamobile.ui.portals.DetailPortalActivity
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.json.responseJson
import com.github.kittinunf.result.Result
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.nav_header_main.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { _ ->
            val scanner = IntentIntegrator(this)
            scanner.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
            scanner.setBeepEnabled(false)
            scanner.initiateScan()
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
                R.id.nav_monsters, R.id.nav_explorations, R.id.nav_elements, R.id.nav_portal_manual), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)



        /*
        // Code pour actualiser l'Inox et la Location à toutes les X secondes
        val headerView: View = navView.getHeaderView(0)
        val txvExplorerInox: TextView = headerView.findViewById(R.id.txvExplorerInox)
        val txvExplorerLocation: TextView = headerView.findViewById(R.id.txvExplorerLocation)
        val txvExplorerName: TextView = headerView.findViewById(R.id.txvExplorerName)

        val handler: Handler = Handler(Looper.getMainLooper())

        handler.post(object : Runnable {
            override fun run() {
                Fuel.get(Services.EXPLORER_SERVICE).responseJson() { _, _, result ->
                    when (result) {
                        is Result.Success -> {
                            val explorer: Explorer = Json { ignoreUnknownKeys = true }.decodeFromString(result.value.content)
                            txvExplorerInox.text = "${explorer.inox.toString()} Inox"
                            txvExplorerLocation.text = "Location Test"
                            txvExplorerName.text = "Username Test"
                        }
                        is Result.Failure -> {

                        }
                    }
                }
                handler.postDelayed(this, 5000)
            }
        })
        */
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    // Ce qu'on fait après le scan du code QR
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK){
            val scan = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
            if (scan != null) {
                if (scan.contents == null){
                    AlertDialogBuilder.showAlertDialog("Error", getString(R.string.scan_cancelled), this)
                } else {
                    Fuel.get("${Services.EXPLORATION_PORTAL_SERVICE}/${scan.contents}").responseJson() { _, _, result ->
                        when(result){
                            is Result.Success -> {
                                val portal: Exploration = Json {ignoreUnknownKeys = true}.decodeFromString(result.value.content)
                                val intent = DetailPortalActivity.newIntent(this, portal)
                                startActivity(intent)
                            }
                            is Result.Failure -> {
                                AlertDialogBuilder.showAlertDialog("Error", "No portal found", this)
                            }
                        }
                    }
                }
            } else {
                super.onActivityResult(requestCode, resultCode, data)
            }
        }
    }

    companion object {
        private const val INTERVAL: Long = 1000*60
    }
}