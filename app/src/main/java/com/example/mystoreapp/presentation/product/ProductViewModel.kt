package com.example.mystoreapp.presentation.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mystoreapp.data.models.Product
import com.example.mystoreapp.data.repository.ProductRepositoryImpl
import com.example.mystoreapp.utils.ResponseStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(private val productRepository: ProductRepositoryImpl) :
    ViewModel() {

        private val _productList = MutableLiveData<MutableList<Product>?>()
        val productList: LiveData<MutableList<Product>?> get() = _productList

        private val _product = MutableLiveData<Product?>()
        val product: LiveData<Product?> get() = _product

        private val _status = MutableLiveData<ResponseStatus<Any>>()
        val status: LiveData<ResponseStatus<Any>> get() = _status

        fun getProductsFromDatabase(){
            viewModelScope.launch {
                val response = productRepository.getProductsFromDatabase()
                if (response is ResponseStatus.Success){
                    if (response.data.size == 0){
                        getProductsFromApi()
                    }
                    else{
                        _productList.value = response.data
                        _status.value = ResponseStatus.Success(response.data)
                    }
                }
                if (response is ResponseStatus.Error){
                    _status.value = ResponseStatus.Error(response.messageId)
                }
            }
        }

    private fun getProductsFromApi() {
        _status.value = ResponseStatus.Loading()
        viewModelScope.launch {
            val response = productRepository.getProductsFromApi()
            if(response is ResponseStatus.Success){
                productRepository.insertProductsIntoDatabase(response.data)
                _productList.value = response.data
                _status.value = ResponseStatus.Success(response.data)
            }
            if (response is ResponseStatus.Error){
                _status.value = ResponseStatus.Success(response.messageId)
            }
        }
    }

    fun getProductById(id: Int){
        viewModelScope.launch {
            _status.value = ResponseStatus.Loading()
            val response = productRepository.getProductById(id)
            if (response is ResponseStatus.Success){
                _product.value = response.data
                _status.value = ResponseStatus.Success(response.data)
            }
            if (response is ResponseStatus.Error){
                _status.value = ResponseStatus.Error(response.messageId)
            }
        }
    }


}