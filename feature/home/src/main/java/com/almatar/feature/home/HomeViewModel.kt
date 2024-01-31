package com.almatar.feature.home

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.almatar.core.common.result.Result
import com.almatar.core.common.result.asResult
import com.almatar.core.data.repository.ProductRepository
import com.almatar.core.domain.GetAllProductsUseCase
import com.almatar.feature.home.ui_state.AllProductsResultUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getAllProductsUseCase: GetAllProductsUseCase,
    private val savedStateHandle: SavedStateHandle,
    private val productRepository: ProductRepository,
) : ViewModel() {

    val searchQuery = savedStateHandle.getStateFlow(key = SEARCH_QUERY, initialValue = "")
    private val isAsc = MutableStateFlow(false)

    val allProductsResultUiState: StateFlow<AllProductsResultUiState> = isAsc.flatMapLatest { asc ->
        searchQuery.flatMapLatest { query ->
            getAllProductsUseCase(query, asc)
                .asResult().map { result ->
                    when (result) {
                        is Result.Success -> result.data.let {
                            if (it.isEmpty()) AllProductsResultUiState.NoProductsFound else AllProductsResultUiState.Success(
                                it
                            )
                        }

                        is Result.Loading -> AllProductsResultUiState.Loading
                        is Result.Error -> AllProductsResultUiState.LoadFailed
                    }
                }
        }

    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = AllProductsResultUiState.Loading,
    )


    fun deleteProduct(productId: Long) {
        viewModelScope.launch {
            productRepository.deleteProductById(productId)
        }
    }

    fun changeSortingById() {
        isAsc.value = !isAsc.value
    }

    fun onSearchQueryChanged(query: String) {
        savedStateHandle[SEARCH_QUERY] = query
    }

}


private const val SEARCH_QUERY = "searchQuery"