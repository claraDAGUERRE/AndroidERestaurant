package fr.isen.daguerre.androiderestaurant

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import fr.isen.daguerre.androiderestaurant.databinding.ActivityDishesBinding
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.isen.daguerre.androiderestaurant.model.Dish
import fr.isen.daguerre.androiderestaurant.model.DishResult
import org.json.JSONObject

class DishesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDishesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDishesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val category = intent.getStringExtra("category_type")
        binding.mainDishTitle.text = category

        binding.dishList.layoutManager = LinearLayoutManager(this)


        //http request api

        val url = "http://test.api.catering.bluecodegames.com/menu"
        val queue = Volley.newRequestQueue(this)

        val jsonObject =JSONObject()
        jsonObject.put("id_shop","1")

        // Request a string response from the provided URL.

        val request = JsonObjectRequest(
            Request.Method.POST, url,jsonObject,
            { response ->
                val gson= Gson()
                val dishresult =gson.fromJson(response.toString(), DishResult::class.java)
                displayDishes(dishresult.data.firstOrNull { it.name_fr == category }?.items ?: listOf())

                Log.d("","$response")
            }, {
                Log.i("","Volley error: $it")
            })

        Volley.newRequestQueue(this).add(request)

        binding.BasketImage.setOnClickListener {
            val intent = Intent(this, BasketActivity::class.java)
            startActivity((intent))
        }
    }
    private fun displayDishes(dishResult: List<Dish>){

        binding.dishList.layoutManager = LinearLayoutManager(this)
        binding.dishList.adapter = CustomAdapter(dishResult) {
            val intent= Intent(this,DetailActivity::class.java)
            intent.putExtra("dish_name",it)
            startActivity(intent)

        }


    }



}

