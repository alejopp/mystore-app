package com.example.mystoreapp.presentation.product

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mystoreapp.R
import com.example.mystoreapp.databinding.FragmentProductBinding
import com.example.mystoreapp.utils.ResponseStatus
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductFragment : Fragment() {

    companion object{
        const val GRID_COLUMNS = 2
    }

    private var _binding: FragmentProductBinding? = null
    private val binding get() = _binding!!
    private val productViewModel: ProductViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductBinding.inflate(inflater, container, false)
        setComponents()
        observeViewModel()
        return binding.root
    }

    private fun observeViewModel() {
        productViewModel.status.observe(viewLifecycleOwner) { status ->
            when (status) {
                is ResponseStatus.Error -> {
                    binding.pbProducts.visibility = View.GONE
                    showErrorDialog(status.messageId)
                }
                is ResponseStatus.Loading -> binding.pbProducts.visibility = View.VISIBLE
                is ResponseStatus.Success -> binding.pbProducts.visibility = View.GONE
            }
        }
        productViewModel.productList.observe(viewLifecycleOwner){ productList ->
            binding.rvProduct.adapter = productList?.let { ProductAdapter(it) }
        }
    }

    private fun showErrorDialog(messageId: Int) {
        AlertDialog.Builder(requireContext())
            .setTitle(R.string.error_message)
            .setMessage(messageId)
            .setPositiveButton(android.R.string.ok) { _, _ -> /** Dissmiss dialog **/ }
            .create()
            .show()
    }

    private fun setComponents() {
        //Get products
        productViewModel.getProductsFromDatabase()
        //Set Product recycler view
        binding.rvProduct.layoutManager = GridLayoutManager(context, GRID_COLUMNS)
    }

}