package fr.isen.daguerre.androiderestaurant

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import android.content.Intent
import android.util.Log




class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val button = findViewById<Button>(R.id.starters)
        val button2 = findViewById<Button>(R.id.dishes)
        val button3 = findViewById<Button>(R.id.deserts)

        button.setOnClickListener{
            val toast = Toast.makeText(this , "you tap on entrees", Toast.LENGTH_SHORT)
            toast.show()
        }
        button2.setOnClickListener{
            val intent = Intent(this, DishesActivity ::class.java)
            val toast = Toast.makeText(this , "you tap on plats", Toast.LENGTH_SHORT)
            toast.show()
            startActivity(intent)
            finish()
            Log.i("info", "home is deleting")
        }
        button3.setOnClickListener{
            val toast = Toast.makeText(this , "you tap on deserts", Toast.LENGTH_SHORT)
            toast.show()

        }
    }
}