package com.example.mystoreapp.presentation.productdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mystoreapp.databinding.FragmentProductDetailBinding
import com.example.mystoreapp.databinding.ItemProductImageBinding
import com.squareup.picasso.Picasso

class ProductDetailAdapter(private val imageList: List<String>?) :
    RecyclerView.Adapter<ProductDetailAdapter.ProductDetailViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductDetailViewHolder {
        return ProductDetailViewHolder(
            ItemProductImageBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ProductDetailViewHolder, position: Int) {
        val productImage = imageList?.get(position)
        Picasso.get().load(productImage).into(holder.binding.ivProductDetailImage)
    }

    override fun getItemCount() = imageList?.size ?: 1

    inner class ProductDetailViewHolder(val binding: ItemProductImageBinding) :
        RecyclerView.ViewHolder(binding.root)
}