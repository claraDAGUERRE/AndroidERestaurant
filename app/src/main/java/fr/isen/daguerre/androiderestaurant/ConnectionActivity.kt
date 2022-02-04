package fr.isen.daguerre.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import fr.isen.daguerre.androiderestaurant.databinding.ActivityConnectionBinding

class ConnectionActivity : AppCompatActivity() {

        private lateinit var binding: ActivityConnectionBinding

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            binding = ActivityConnectionBinding.inflate(layoutInflater)
            setContentView(binding.root)

            binding.btnLogin.setOnClickListener {
                if (binding.userName.text.trim().isNotEmpty() || binding.userPassword.text.trim()
                        .isNotEmpty()
                ) {
                    Toast.makeText(this, "Entrée un identifiant", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Entrée un mot de passe", Toast.LENGTH_LONG).show()
                }
            }

            binding.tvRegister.setOnClickListener {
                val intent = Intent(this, SignUpActivity::class.java)
                startActivity((intent))
            }
        }
}

