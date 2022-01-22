package com.jxlopez.menumealdb.presentation.meal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jxlopez.menumealdb.databinding.ItemMealBinding
import com.jxlopez.menumealdb.models.meals.SingleMeal
import com.jxlopez.menumealdb.utils.extensions.loadImageUrl

class MealAdapter
    : RecyclerView.Adapter<MealAdapter.HoursViewHolder>() {
    lateinit var onItemClick: (SingleMeal) -> Unit

    private val diffCallback = object : DiffUtil.ItemCallback<SingleMeal>(){
        override fun areItemsTheSame(oldItem: SingleMeal, newItem: SingleMeal): Boolean {
            return oldItem.idMeal == newItem.idMeal
        }

        override fun areContentsTheSame(oldItem: SingleMeal, newItem: SingleMeal): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    private val differ = AsyncListDiffer(this,diffCallback)

    fun submitList(list: List<SingleMeal>) = differ.submitList(list)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoursViewHolder {
        val binding = ItemMealBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return HoursViewHolder(binding)
    }

    override fun getItemCount() = differ.currentList.size

    fun setOnItemClickListener(block: (SingleMeal) -> Unit) {
        onItemClick = block
    }

    override fun onBindViewHolder(holder: HoursViewHolder, position: Int) {
        with(holder){
            with(differ.currentList[position]) {
                binding.tvTitle.text = strMeal
                binding.ivImageMeal.loadImageUrl(strMealThumb)

                holder.itemView.setOnClickListener {
                    if (::onItemClick.isInitialized)
                        onItemClick(this)
                }
            }
        }
    }

    inner class HoursViewHolder(val binding: ItemMealBinding)
        : RecyclerView.ViewHolder(binding.root)

}