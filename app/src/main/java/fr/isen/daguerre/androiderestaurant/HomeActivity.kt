package fr.isen.daguerre.androiderestaurant

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val button = findViewById<Button>(R.id.starters)
        val button2 = findViewById<Button>(R.id.Plats)
        val button3 = findViewById<Button>(R.id.Deserts)
    }
}