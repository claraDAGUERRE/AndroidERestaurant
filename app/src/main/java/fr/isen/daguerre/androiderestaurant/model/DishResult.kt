package fr.isen.daguerre.androiderestaurant.model

import java.io.Serializable


data class DishResult(
    val data : List<Category>
) : Serializable
