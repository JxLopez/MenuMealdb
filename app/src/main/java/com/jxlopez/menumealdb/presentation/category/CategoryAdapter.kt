package com.jxlopez.menumealdb.presentation.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jxlopez.menumealdb.databinding.ItemCategoryBinding
import com.jxlopez.menumealdb.models.categories.Category
import com.jxlopez.menumealdb.utils.extensions.loadImageUrl

class CategoryAdapter
    : RecyclerView.Adapter<CategoryAdapter.HoursViewHolder>() {
    lateinit var onItemClick: (Category) -> Unit

    private val diffCallback = object : DiffUtil.ItemCallback<Category>(){
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.idCategory == newItem.idCategory
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    private val differ = AsyncListDiffer(this,diffCallback)

    fun submitList(list: List<Category>) = differ.submitList(list)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoursViewHolder {
        val binding = ItemCategoryBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return HoursViewHolder(binding)
    }

    override fun getItemCount() = differ.currentList.size

    fun setOnItemClickListener(block: (Category) -> Unit) {
        onItemClick = block
    }

    override fun onBindViewHolder(holder: HoursViewHolder, position: Int) {
        with(holder){
            with(differ.currentList[position]) {
                binding.tvCategory.text = strCategory
                binding.ivPhotoCategory.loadImageUrl(strCategoryThumb)

                binding.bgContent.setOnClickListener {
                    if (::onItemClick.isInitialized)
                        onItemClick(this)
                }
            }
        }
    }

    inner class HoursViewHolder(val binding: ItemCategoryBinding)
        :RecyclerView.ViewHolder(binding.root)

}