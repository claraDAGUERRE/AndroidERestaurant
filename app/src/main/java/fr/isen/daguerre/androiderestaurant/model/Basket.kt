package fr.isen.daguerre.androiderestaurant.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Basket (
    val data : List<BasketIems>
) : Serializable