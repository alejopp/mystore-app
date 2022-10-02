package com.example.mystoreapp.presentation.product

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mystoreapp.data.models.Product
import com.example.mystoreapp.databinding.ItemProductBinding


class ProductAdapter(private val productList: List<Product>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            ItemProductBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        )
    }

    override fun onBindViewHolder(productViewholder: ProductViewHolder, position: Int) {
        val product = productList[position]
        productViewholder.binding.tvProductTitle.text = product.title
        productViewholder.binding.tvProductDescriptionValue.text = product.description
    }

    override fun getItemCount() = productList.size

    inner class ProductViewHolder(val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root)

}