package fr.isen.daguerre.androiderestaurant.model

import java.io.Serializable

data class BasketIems (
    val dish : Dish,
    var quantity : Int
) : Serializable
