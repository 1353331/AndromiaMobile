package ca.qc.cstj.andromiamobile.ui.account

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ca.qc.cstj.andromiamobile.MainActivity
import ca.qc.cstj.andromiamobile.databinding.ActivityCreationBinding
import ca.qc.cstj.andromiamobile.databinding.ActivityLoginBinding

class CreationActivity: AppCompatActivity() {

    private lateinit var binding: ActivityCreationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreationBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun btnCreateClick(view: View?){
        // Lorsque l'on clique pour créer le compte
        val username = binding.edtUsername.editableText
        val email = binding.edtEmail.editableText
        val password = binding.edtPassword.editableText
        val confirmation = binding.edtConfirmation.editableText

        if(password.isNotBlank() && confirmation.isNotBlank() && username.isNotBlank() && email.isNotBlank() &&
            password.toString() == confirmation.toString()){
            //TODO:Envoie en BD l'utilisateur
            Toast.makeText(this, "Utilisateur créé!", Toast.LENGTH_LONG).show()

            val intent = LoginActivity.newIntent(this)

            startActivity(intent)

            username.clear()
            email.clear()
            password.clear()
            confirmation.clear()
        }
        else{
            if(username.isBlank()){
                Toast.makeText(this, "Veuillez entrer un nom d'utilisateur.", Toast.LENGTH_LONG).show()
            }
            if(email.isBlank()){
                Toast.makeText(this, "Veuillez entrer un adresse courriel.", Toast.LENGTH_LONG).show()
            }
            if(password.isBlank()){
                Toast.makeText(this, "Veuillez entrer un mot de passe.", Toast.LENGTH_LONG).show()
            }
            if(confirmation.isBlank()){
                Toast.makeText(this, "Veuillez confirmer votre mot de passe.", Toast.LENGTH_LONG).show()
            }
            if(password.toString() != confirmation.toString()){
                Toast.makeText(this, "Les mots de passe ne sont pas identiques.", Toast.LENGTH_LONG).show()
            }
        }
    }

    companion object  {
        fun newIntent(context: Context): Intent {
            return Intent(context, CreationActivity::class.java)
        }
    }
}