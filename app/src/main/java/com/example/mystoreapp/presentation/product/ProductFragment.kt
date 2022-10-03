package com.example.mystoreapp.presentation.product

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
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
    private var selectedCategory = "rating"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductBinding.inflate(inflater, container, false)
        initComponents()
        observeViewModel()
        listenEvents()
        return binding.root
    }

    private fun listenEvents() {
        binding.tbProductSearch.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_filter -> {
                    val categories = resources.getStringArray(R.array.product_categories)
                    val builder = AlertDialog.Builder(requireContext())
                    builder
                        .setTitle(getString(R.string.filter_by))
                        .setSingleChoiceItems(categories, -1) { _, selected ->
                            this.selectedCategory = categories[selected]
                        }
                        .setPositiveButton(android.R.string.ok){dialog,_ ->
                            productViewModel.filterBy(this.selectedCategory)
                         }
                        .setNegativeButton(android.R.string.cancel){dialog,_ ->
                            dialog.dismiss()
                         }
                        .show()
                    true
                }
                else -> false
            }
        }
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

    private fun initComponents() {
        //Get products
        productViewModel.getProductsFromDatabase()
        //Set Product recycler view
        binding.rvProduct.layoutManager = GridLayoutManager(context, GRID_COLUMNS)
    }

/*    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.top_bar_search, menu)
        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView
        searchView.queryHint = "Help me"
        super.onCreateOptionsMenu(menu, inflater)
    }*/

/*    fun onCreateOptionsMenu(menu: Menu): Boolean {
        context.inflate(R.menu.search_actions, menu)
        val searchViewItem: MenuItem = menu.findItem(R.id.action_search)
        // Get the SearchView and set the searchable configuration
        val searchManager = getSystemService<Any>(Context.SEARCH_SERVICE) as SearchManager?
        val searchView = searchViewItem.getActionView() as SearchView
        searchView.queryHint = "Search for Product,Brands..."
        searchView.setSearchableInfo(searchManager!!.getSearchableInfo(getComponentName()))
        searchView.isIconifiedByDefault = false // Do not iconify the widget; expand it by defaul
        val queryTextListener: SearchView.OnQueryTextListener =
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextChange(newText: String): Boolean {
                    // This is your adapter that will be filtered
                    Toast.makeText(
                        ApplicationProvider.getApplicationContext<Context>(),
                        "textChanged :$newText", Toast.LENGTH_LONG
                    ).show()
                    return true
                }

                override fun onQueryTextSubmit(query: String): Boolean {
                    // **Here you can get the value "query" which is entered in the search box.**
                    Toast.makeText(
                        ApplicationProvider.getApplicationContext<Context>(),
                        "searchvalue :$query", Toast.LENGTH_LONG
                    ).show()
                    return true
                }
            }
        searchView.setOnQueryTextListener(queryTextListener)
        return true
    }*/

}