package com.example.retrofitmealnavdrawerhomework.ui.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitmealnavdrawerhomework.R
import com.example.retrofitmealnavdrawerhomework.ui.Model.Meal
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_beef.view.*
import kotlinx.android.synthetic.main.item_chicken.view.*
import kotlinx.android.synthetic.main.item_seafood.view.*

class MealAdapter :RecyclerView.Adapter<MealAdapter.MealViewHolder>() {

    var mealResultList: List<Meal> = listOf()

    fun updateMealList(result: List<Meal>) {
        this.mealResultList = result
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        return MealViewHolder.from(parent)
    }

    override fun getItemCount(): Int = mealResultList.size

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        val item = mealResultList[position]
        holder.bindMeal(item)
        Log.d("size>>>>", mealResultList.size.toString())
    }

    class MealViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindMeal(meal: Meal) {

            Picasso.get().load(meal.strMealThumb).placeholder(R.drawable.profile)
                .into(itemView.imageBeef)
            itemView.tvBeefName.text = meal.strMeal
            itemView.tvBeefName.isSelected=true
            Log.d("BeefName>>>>", itemView.tvBeefName.text.toString())

            /*Picasso.get().load(meal.strMealThumb).placeholder(R.drawable.profile)
                .into(itemView.imageChicken)
            Picasso.get().load(meal.strMealThumb).placeholder(R.drawable.profile)
                .into(itemView.imageSeafood)
            itemView.tvChickenName.text = meal.strMeal
            itemView.tvSeafoodName.text = meal.strMeal
            Log.d("ChickenName>>>>", itemView.tvChickenName.text.toString())
            Log.d("SeafoodName>>>>", itemView.tvSeafoodName.text.toString())*/
        }

        companion object {
            fun from(parent: ViewGroup): MealViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)

                val viewBeef = layoutInflater.inflate(R.layout.item_beef, parent, false)
                //val viewChicken = layoutInflater.inflate(R.layout.item_chicken, parent, false)
                //val viewSeafood = layoutInflater.inflate(R.layout.item_seafood, parent, false)

                return MealViewHolder(viewBeef)
                //return MealViewHolder(viewChicken)
                //return MealViewHolder(viewSeafood)
            }
        }

    }
}