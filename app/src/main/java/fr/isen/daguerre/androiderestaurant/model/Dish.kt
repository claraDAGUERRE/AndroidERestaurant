package fr.isen.daguerre.androiderestaurant.model

import java.io.Serializable

data class Dish(
    val name_fr : String,
    val images : List<String>,
    val ingredients : List<Ingredients>,
    val prices : List<Price>
) : Serializable