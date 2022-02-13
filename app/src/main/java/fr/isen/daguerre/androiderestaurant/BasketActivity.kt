package fr.isen.daguerre.androiderestaurant

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import fr.isen.daguerre.androiderestaurant.databinding.ActivityBasketBinding
import fr.isen.daguerre.androiderestaurant.model.Basket
import fr.isen.daguerre.androiderestaurant.model.BasketIems
import java.io.File

class BasketActivity : AppCompatActivity() {
    private lateinit var binding : ActivityBasketBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBasketBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val file = File(cacheDir.absolutePath + "/basket.json")

        if (file.exists()) {
            val basketIems : List<BasketIems> = Gson().fromJson(file.readText(), Basket::class.java).data
            display(basketIems)
        }

        val quantity = getString(R.string.basketTotalQuantity) + this.getSharedPreferences(getString(R.string.spFileName), Context.MODE_PRIVATE).getInt(getString(R.string.spTotalQuantity), 0).toString()
        binding.basketTotalQuantity.text = quantity

        val price = getString(R.string.totalPrice) + this.getSharedPreferences(getString(R.string.spFileName), Context.MODE_PRIVATE).getFloat(getString(R.string.spTotalPrice), 0.0f).toString()
        binding.basketTotalPrice.text = price

        binding.basketButtonBuy.setOnClickListener {
            val userId = this.getSharedPreferences(getString(R.string.spFileName), Context.MODE_PRIVATE).getInt(getString(R.string.spUserId), 0)

            if (userId == 0) {
                startActivity(Intent(this, ConnexionActivity::class.java))
            } else {
                startActivity(Intent(this, SignUpActivity::class.java))
            }
        }

        binding.basketButtonDeleteAll.setOnClickListener {
            deleteBasketData()
            finish()
        }
    }

    private fun display(dishesList: List<BasketIems>) {
        binding.basketList.layoutManager = LinearLayoutManager(this)
        binding.basketList.adapter = BasketAdapter(dishesList) {
            deleteDishBasket(it)
        }
    }

    private fun deleteDishBasket(dish : BasketIems) {
        val file = File(cacheDir.absolutePath + "/basket.json")
        var dishesBasket: List<BasketIems> = ArrayList()

        if (file.exists()) {
            dishesBasket = Gson().fromJson(file.readText(), Basket::class.java).data
            dishesBasket = dishesBasket - dish
            updateSharedPreferences(dish.quantity, dish.dish.prices[0].price.toFloat())
        }

        file.writeText(Gson().toJson(Basket(dishesBasket)))

        finish()
        this.recreate()
    }

    private fun deleteBasketData() {
        File(cacheDir.absolutePath + "/basket.json").delete()
        this.getSharedPreferences(getString(R.string.spFileName), Context.MODE_PRIVATE).edit().remove(getString(R.string.spTotalPrice)).apply()
        this.getSharedPreferences(getString(R.string.spFileName), Context.MODE_PRIVATE).edit().remove(getString(R.string.spTotalQuantity)).apply()
        Toast.makeText(this, getString(R.string.basketDeleteAllTxt), Toast.LENGTH_SHORT).show()
    }

    private fun updateSharedPreferences(quantity: Int, price: Float) {
        val sharedPreferences = this.getSharedPreferences(getString(R.string.spFileName), Context.MODE_PRIVATE)

        val oldQuantity = sharedPreferences.getInt(getString(R.string.spTotalQuantity), 0)
        val newQuantity = oldQuantity + quantity
        sharedPreferences.edit().putInt(getString(R.string.spTotalQuantity), newQuantity).apply()

        val oldPrice = sharedPreferences.getFloat(getString(R.string.spTotalPrice), 0.0f)
        val newPrice = oldPrice - price
        sharedPreferences.edit().putFloat(getString(R.string.spTotalPrice), newPrice).apply()
    }
}