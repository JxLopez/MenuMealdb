package com.jxlopez.menumealdb.presentation.category

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.jxlopez.menumealdb.databinding.ItemCategoryBinding
import com.jxlopez.menumealdb.models.categories.Category
import com.jxlopez.menumealdb.utils.extensions.loadImageUrl

class CategoryAdapter(private val categoryList: List<Category>)
    : RecyclerView.Adapter<CategoryAdapter.HoursViewHolder>() {
    lateinit var onItemClick: (Category) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoursViewHolder {
        val binding = ItemCategoryBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return HoursViewHolder(binding)
    }

    override fun getItemCount() = categoryList.size

    fun setOnItemClickListener(block: (Category) -> Unit) {
        onItemClick = block
    }

    override fun onBindViewHolder(holder: HoursViewHolder, position: Int) {
        with(holder){
            with(categoryList[position]) {
                binding.tvCategory.text = strCategory
                binding.ivPhotoCategory.loadImageUrl(strCategoryThumb)
                //GlideApp.with(holder.itemView.context)
                  //  .load(badgeUrl)
                    //.into(binding.topLearnerImage)

                holder.itemView.setOnClickListener {
                    Toast.makeText(holder.itemView.context, strCategory,
                        Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    inner class HoursViewHolder(val binding: ItemCategoryBinding)
        :RecyclerView.ViewHolder(binding.root)

}