package fr.isen.daguerre.androiderestaurant.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class DishResult(val data: List<categoryModel>) :Serializable

data class categoryModel(val name_fr : String, val items : List<DishModel>): Serializable

data class DishModel(val name_fr: String, @SerializedName("images") val pictures: List<String>, val prices: List<Price>, val ingredients: List<Ingredients>) : Serializable
{
    fun getFirstPicture() = if(pictures[0].isNotEmpty()) pictures[0] else null
    fun getFormattedPrice()= prices[0].price + "€"
}
data class Price(val price: String): Serializable

data class Ingredients(val name_fr: String): Serializable