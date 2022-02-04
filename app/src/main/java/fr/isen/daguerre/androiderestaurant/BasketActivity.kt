package fr.isen.daguerre.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import fr.isen.daguerre.androiderestaurant.databinding.ActivityBasketBinding

class BasketActivity : AppCompatActivity() {
    private lateinit var binding : ActivityBasketBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBasketBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        binding.buttonOrder.setOnClickListener {
            changeActivity()
            val snack = Snackbar.make(it, "Votre commande est enregistrée", Snackbar.LENGTH_LONG)
            snack.show()
        }

    }

    private fun changeActivity() {
        val intent = Intent(this@BasketActivity, ConnectionActivity::class.java)
        startActivity(intent)
    }
}