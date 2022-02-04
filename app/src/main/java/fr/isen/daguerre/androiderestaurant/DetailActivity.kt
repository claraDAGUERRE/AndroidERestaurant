package fr.isen.daguerre.androiderestaurant

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.squareup.picasso.Picasso
import fr.isen.daguerre.androiderestaurant.databinding.ActivityBasketBinding
import fr.isen.daguerre.androiderestaurant.databinding.ActivityDetailBinding
import fr.isen.daguerre.androiderestaurant.model.Dish
import fr.isen.daguerre.androiderestaurant.model.DishModel

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detail = intent.getSerializableExtra("dish_name") as DishModel
        initDetail(detail)
    }
    private fun initDetail(dish: DishModel){
        var nbInBucket = 1
        binding.buttonPlus.setOnClickListener{
            changeNumber(dish,1)
        }
        binding.buttonMoins.setOnClickListener{
            changeNumber(dish,0)
        }
        binding.detailTitle.text= dish.name_fr
        binding.dishPhotoPager.adapter=DishPictureAdapter(this, dish.pictures)

        var ingredients = ""
        for(i in dish.ingredients) {
            ingredients += if(i==dish.ingredients.last()){
                (i.name_fr)
            }else{
                (i.name_fr) + ","
            }
        }
        binding.detailIngredients.text = ingredients

        binding.buttonAdd.setOnClickListener {
            changeActivity()
        }
    }
    private fun changeNumber(dish: DishModel, minusOrplus: Int) {
        var nb = (binding.dishNumber.text as String).toInt()
        if (minusOrplus==0) {
            if (nb == 1) {
            } else {
                nb--
            }
        }else{
                nb++
            }
            binding.dishNumber.text = nb.toString()
            changePrice(dish, nb)
    }

    @SuppressLint("SetTextI18n")
    private fun changePrice(dish: DishModel, nb:Int){
        var newPrice = dish.prices[0].price.toFloatOrNull()
        newPrice=newPrice?.times(nb)
        binding.dishNumber.text="$newPrice €"
    }

    private fun changeActivity() {
        val intent = Intent(this@DetailActivity, BasketActivity::class.java)
        startActivity(intent)
    }
}
