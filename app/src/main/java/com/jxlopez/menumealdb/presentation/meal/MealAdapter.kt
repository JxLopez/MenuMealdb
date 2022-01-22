package com.jxlopez.menumealdb.presentation.meal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jxlopez.menumealdb.databinding.ItemMealBinding
import com.jxlopez.menumealdb.models.categories.Category
import com.jxlopez.menumealdb.models.meals.SingleMeal
import com.jxlopez.menumealdb.utils.extensions.loadImageUrl

class MealAdapter(private val mealsList: List<SingleMeal>)
    : RecyclerView.Adapter<MealAdapter.HoursViewHolder>() {
    lateinit var onItemClick: (Category) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoursViewHolder {
        val binding = ItemMealBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return HoursViewHolder(binding)
    }

    override fun getItemCount() = mealsList.size

    fun setOnItemClickListener(block: (Category) -> Unit) {
        onItemClick = block
    }

    override fun onBindViewHolder(holder: HoursViewHolder, position: Int) {
        with(holder){
            with(mealsList[position]) {
                binding.tvTitle.text = strMeal
                binding.ivImageMeal.loadImageUrl(strMealThumb)

                holder.itemView.setOnClickListener {
                    //Toast.makeText(holder.itemView.context, strCategory,
                        //Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    inner class HoursViewHolder(val binding: ItemMealBinding)
        : RecyclerView.ViewHolder(binding.root)

}