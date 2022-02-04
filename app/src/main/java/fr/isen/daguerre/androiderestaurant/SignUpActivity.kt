package fr.isen.daguerre.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import fr.isen.daguerre.androiderestaurant.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {
            if (binding.userNameReg.text.trim().isNotEmpty() || binding.userPasswordReg.text.trim()
                    .isNotEmpty()
            ) {
                Toast.makeText(this, "Entrée un identifiant", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Entrée un mot de passe", Toast.LENGTH_LONG).show()
            }
        }

        binding.alreadyAccount.setOnClickListener {
            val intent = Intent(this, ConnectionActivity::class.java)
            startActivity((intent))
        }

        private fun verifify

    }
}