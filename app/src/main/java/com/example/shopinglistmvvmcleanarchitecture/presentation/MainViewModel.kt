package com.example.shopinglistmvvmcleanarchitecture.presentation


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopinglistmvvmcleanarchitecture.data.ShopListRepositoryImpl
import com.example.shopinglistmvvmcleanarchitecture.domain.DellItemUseCase
import com.example.shopinglistmvvmcleanarchitecture.domain.EditItemUseCase
import com.example.shopinglistmvvmcleanarchitecture.domain.GetShopListUseCase
import com.example.shopinglistmvvmcleanarchitecture.domain.ShopItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MainViewModel (application: Application): AndroidViewModel(application) {

    private val repository = ShopListRepositoryImpl(application)

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val dellItemUseCase = DellItemUseCase(repository)
    private val editItemUseCase = EditItemUseCase(repository)


    val  shopList = getShopListUseCase.getShopList()


    fun dellItem(shopItem: ShopItem){
        viewModelScope.launch {
            dellItemUseCase.dellItem(shopItem)
        }

    }

    fun editItem(shopItem: ShopItem){

        viewModelScope.launch {
            val newItem = shopItem.copy(enabled = !shopItem.enabled)
            editItemUseCase.editItem(newItem)
        }

    }


}