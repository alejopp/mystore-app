package com.example.mystoreapp.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mystoreapp.databinding.ItemCategoryBinding

class CategoryAdapter(private val categoryList: List<String>):
RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(categoryViewHolder: CategoryViewHolder, position: Int) {
        val category = categoryList[position]
        categoryViewHolder.binding.tvHomeCategoryName.text = category
    }

    override fun getItemCount() = categoryList.size

    inner class CategoryViewHolder(val binding: ItemCategoryBinding):
        RecyclerView.ViewHolder(binding.root)

}

