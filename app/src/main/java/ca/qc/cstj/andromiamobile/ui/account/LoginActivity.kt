package ca.qc.cstj.andromiamobile.ui.account

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import ca.qc.cstj.andromiamobile.MainActivity
import ca.qc.cstj.andromiamobile.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun btnConnectionClick(view: View?) {

        val username = binding.edtUsername.editableText
        val password = binding.edtPassword.editableText


        val intent = MainActivity.newIntent(this, username.toString())
        startActivity(intent)

        /*if(password.toString() == GOOD_PASSWORD && username.isNotBlank()) {
            //TODO: Changer d'activity
            //val intent = PlanetsActivity.createIntent(this,username.toString())
            val intent = BikiniBottomActivity.newIntent(this,username.toString())
            startActivity(intent)
        } else {
            Toast.makeText(this, "Mauvais mot de passe", Toast.LENGTH_LONG).show()
        }*/
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