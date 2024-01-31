package com.almatar.feature.upsert_product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.almatar.core.data.model.Product
import com.almatar.core.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpsertViewModel @Inject constructor(
    private val productRepository: ProductRepository,
) : ViewModel() {


    fun upsertProduct(product: Product) {
        viewModelScope.launch {
            productRepository.insertOrReplaceProduct(product)
        }
    }

}