package com.example.mystoreapp.presentation.productdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mystoreapp.R
import com.example.mystoreapp.databinding.FragmentProductDetailBinding
import com.example.mystoreapp.presentation.product.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailFragment : Fragment() {

    val args: ProductDetailFragmentArgs by navArgs()

    private var _binding: FragmentProductDetailBinding? = null
    private val binding get() = _binding!!
    private val productViewModel: ProductViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        binding.tvProductDetailTitle.text = args.productId.toString()
        initComponents()
        observeViewModel()
        return binding.root
    }

    private fun observeViewModel() {
        productViewModel.product.observe(viewLifecycleOwner) { product ->
            binding.tvProductDetailTitle.text =
                getString(R.string.product_name_format, product?.title)
            binding.tvProductDetailPrice.text =
                getString(R.string.product_price_format, product?.price)
            binding.tvProductDetailDiscount.text =
                getString(R.string.product_discount_format, product?.discountPercentage)
            binding.tvProductDetailRate.text = product?.rating.toString()
            binding.tvProductDetailStock.text =
                getString(R.string.product_stock_format, product?.stock)
            binding.tvProductDetailBrand.text =
                getString(R.string.product_brand_format, product?.brand)
            binding.tvProductDetailCategory.text =
                getString(R.string.product_category_format, product?.category)
            binding.tvProductDetailDescription.text =
                getString(R.string.product_description_format, product?.description)
            binding.rvProductDetailPhoto.adapter = ProductDetailAdapter(product?.images)
        }
    }

    private fun initComponents() {
        productViewModel.getProductById(args.productId)
        binding.rvProductDetailPhoto.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

}