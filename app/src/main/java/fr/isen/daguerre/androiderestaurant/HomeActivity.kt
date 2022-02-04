package fr.isen.daguerre.androiderestaurant


import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import android.content.Intent
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import fr.isen.daguerre.androiderestaurant.databinding.ActivityHomeBinding


class HomeActivity : AppCompatActivity() {
    lateinit var binding : ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        binding.starters.setOnClickListener{
            changeActivity(getString(R.string.home_starters))

        //val toast = Toast.makeText(this , "you tap on entrees", Toast.LENGTH_SHORT)
            //toast.show()
        }
        binding.dishes.setOnClickListener{
            changeActivity(getString(R.string.home_dishes))

           // val intent = Intent(this, DishesActivity ::class.java)
           // val toast = Toast.makeText(this , "you tap on plats", Toast.LENGTH_SHORT)
           // toast.show()
           // Log.i("info", "home is deleting")
          //  startActivity(intent)
           // changeActivity(getString(R.string.activity_dishes))


        }
        binding.desserts.setOnClickListener{
            changeActivity(getString(R.string.home_desserts))
           // val toast = Toast.makeText(this , "you tap on deserts", Toast.LENGTH_SHORT)
           // toast.show()

        }

    }
    private fun changeActivity( category: String ) {
        val intent = Intent(this@HomeActivity, DishesActivity::class.java)
        intent.putExtra("category_type", category)
        // Log.i("INFO",  "end of HomeActivity» )
        startActivity(intent)
    }
}