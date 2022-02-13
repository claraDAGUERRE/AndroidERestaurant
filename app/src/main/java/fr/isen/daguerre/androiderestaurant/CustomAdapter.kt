package fr.isen.daguerre.androiderestaurant

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.daguerre.androiderestaurant.R
import fr.isen.daguerre.androiderestaurant.databinding.CategoryCellBinding
import fr.isen.daguerre.androiderestaurant.model.Dish

class CustomAdapter(val dishes: List<Dish>, val onDishClicked: (Dish) -> Unit) : RecyclerView.Adapter<CustomAdapter.DishViewHolder>() {

    class DishViewHolder(val binding: CategoryCellBinding): RecyclerView.ViewHolder(binding.root){
        val items = binding.items
        val prices= binding.prices
        val pictures= binding.pictures
    }
    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val binding = CategoryCellBinding
            .inflate(LayoutInflater.from(parent.context) ,parent , false)

        return DishViewHolder(binding)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: DishViewHolder, position: Int) {
        holder.items.text = dishes[position].name_fr

        Picasso.get()
            .load(dishes[position].images[0])
            .error(R.drawable.bistrologo)
            .placeholder(R.drawable.bistrologo)
            .into(holder.pictures)

       // holder.prices.text = "${items.prices[0].price} €"

        holder.itemView.setOnClickListener{
            onDishClicked( dishes[position])
        }



    }

    // return the number of the items in the list
    override fun getItemCount(): Int = dishes.size


}