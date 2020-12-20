package ca.qc.cstj.andromiamobile.ui.account

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ca.qc.cstj.andromiamobile.MainActivity
import ca.qc.cstj.andromiamobile.databinding.ActivityLoginBinding
import ca.qc.cstj.andromiamobile.helpers.Services
import com.github.kittinunf.fuel.core.extensions.jsonBody
import com.github.kittinunf.fuel.core.isSuccessful
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.failure
import com.github.kittinunf.result.success
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun btnConnectionCheatClick(view: View?) {

        //On va chercher un user dans la base de donnée, si la BD a été réinitialisé,
        // on peut le créer dans l'application directement
        val username = "explorateur@hotmail.com"
        val password = "1234"

        if(password.isNotBlank() && username.isNotBlank()) {
            val service = Services.CONNECTION_SERVICE
            val compte = "{\"email\":\"${username}\",\"password\":\"${password}\"}"

            service.httpPost().jsonBody(compte).response{ _, response, result ->
                result.success {
                    val resulatBody = Json.parseToJsonElement(response.data.decodeToString())

                    val accessToken = resulatBody.jsonObject["accessToken"].toString()
                    val refreshToken = resulatBody.jsonObject["refreshToken"].toString()

                    val intent = MainActivity.newIntent(this, username.toString())
                    startActivity(intent)

                }
                result.failure {
                    Toast.makeText(this, "Échec de connection: Compte inexistant ou mauvais mot de passe", Toast.LENGTH_LONG).show()
                }
            }
        } else {
            Toast.makeText(this, "Mauvais mot de passe", Toast.LENGTH_LONG).show()
        }
    }

    fun btnConnectionClick(view: View?) {

        val email = binding.edtEmail.editableText
        val password = binding.edtPassword.editableText

        if(password.isNotBlank() && email.isNotBlank()) {
            val service = Services.CONNECTION_SERVICE
            val compte = "{\"email\":\"${email}\",\"password\":\"${password}\"}"

            service.httpPost().jsonBody(compte).response{ _, response, result ->
                result.success {
                    val resulatBody = Json.parseToJsonElement(response.data.decodeToString())

                    val accessToken = resulatBody.jsonObject["accessToken"].toString()
                    val refreshToken = resulatBody.jsonObject["refreshToken"].toString()

                    val intent = MainActivity.newIntent(this, email.toString())
                    startActivity(intent)

                }
                result.failure {
                    Toast.makeText(this, "Échec de connection: Compte inexistant ou mauvais mot de passe", Toast.LENGTH_LONG).show()
                }
            }
        } else {
            Toast.makeText(this, "Mauvais mot de passe", Toast.LENGTH_LONG).show()
        }
    }

    fun btnCreationClick(view: View?){
        val intent = CreationActivity.newIntent(this)
        startActivity(intent)
    }

    companion object  {
        fun newIntent(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }
}