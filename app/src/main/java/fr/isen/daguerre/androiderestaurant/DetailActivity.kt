package fr.isen.daguerre.androiderestaurant

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import fr.isen.daguerre.androiderestaurant.databinding.ActivityDetailBinding
import fr.isen.daguerre.androiderestaurant.model.BasketIems
import fr.isen.daguerre.androiderestaurant.model.Dish
import fr.isen.daguerre.androiderestaurant.model.Basket
import org.json.JSONObject
import java.io.File

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detail = intent.getSerializableExtra("dish_name") as Dish
        initDetail(detail)
    }

    private fun initDetail(dish: Dish) {

        var nbInBucket = 1
        binding.buttonPlus.setOnClickListener {
            changeNumber(dish, 1)
        }
        binding.buttonMoins.setOnClickListener {
            changeNumber(dish, 0)
        }
        binding.detailTitle.text = dish.name_fr

        val txt = getString(R.string.totalPrice) + dish.prices[0].price + " €"
        binding.buttonAdd.text = txt

        binding.dishPhotoPager.adapter = DishPictureAdapter(this, dish.images)

        binding.buttonAdd.setOnClickListener {
            Toast.makeText(this, getString(R.string.addToBasket), Toast.LENGTH_SHORT).show()
            updateFile(BasketIems(dish, nbInBucket))
            updateSharedPreferences(nbInBucket, (dish.prices[0].price.toFloat() * nbInBucket))
            finish()
            changeActivity()
        }

        var ingredients = ""
        for (i in dish.ingredients) {
            ingredients += if (i == dish.ingredients.last()) {
                (i.name_fr)
            } else {
                (i.name_fr) + ","
            }
        }
        binding.detailIngredients.text = ingredients

    }

    private fun changeNumber(dish: Dish, minusOrplus: Int) {
        var nb = (binding.dishNumber.text as String).toInt()
        if (minusOrplus == 0) {
            if (nb == 1) {
            } else {
                nb--
            }
        } else {
            nb++
        }
        binding.dishNumber.text = nb.toString()
        val price = dish.prices[0].price.toFloat()
        val totalPrice = getString(R.string.totalPrice) + price * nb + " €"
        binding.buttonAdd.text = totalPrice
        changePrice(dish, nb)

        /*val gson = GsonBuilder().setPrettyPrinting().create()
        binding.buttonAdd.setOnClickListener{
            var command = JSONObject("""{"dish":"${binding.detailTitle.text}","quantity":"${binding.dishNumber.text}""")
            val jsonList : String = gson.toJson(command)

            File(cacheDir.absolutePath+"inBasket.json").writeText(jsonList)
        changeActivity()}*/
    }


    @SuppressLint("SetTextI18n")
    private fun changePrice(dish: Dish, nb: Int) {
        var newPrice = dish.prices[0].price.toFloatOrNull()
        newPrice = newPrice?.times(nb)
        binding.buttonAdd.text = "$newPrice €"
    }
    private fun updateFile(dishBasket : BasketIems) {
        val file = File(cacheDir.absolutePath + "/basket.json")
        var dishesBasket: List<BasketIems> = ArrayList()

        if (file.exists()) {
            dishesBasket = Gson().fromJson(file.readText(), Basket::class.java).data
        }

        var dupli = false
        for (i in dishesBasket.indices) {
            if (dishesBasket[i].dish == dishBasket.dish) {
                dishesBasket[i].quantity += dishBasket.quantity
                dupli = true
            }
        }

        if (!dupli) {
            dishesBasket = dishesBasket + dishBasket
        }

        file.writeText(Gson().toJson(Basket(dishesBasket)))
    }

    private fun updateSharedPreferences(quantity: Int, price: Float) {
        val sharedPreferences = this.getSharedPreferences(getString(R.string.spFileName), Context.MODE_PRIVATE)

        val oldQuantity = sharedPreferences.getInt(getString(R.string.spTotalQuantity), 0)
        val newQuantity = oldQuantity + quantity
        sharedPreferences.edit().putInt(getString(R.string.spTotalQuantity), newQuantity).apply()

        val oldPrice = sharedPreferences.getFloat(getString(R.string.spTotalPrice), 0.0f)
        val newPrice = oldPrice + price
        sharedPreferences.edit().putFloat(getString(R.string.spTotalPrice), newPrice).apply()
    }

    private fun changeActivity() {
        val intent = Intent(this@DetailActivity, BasketActivity::class.java)
        startActivity(intent)
    }
}
