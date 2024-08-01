package com.example.shopinglistmvvmcleanarchitecture.presentation


import androidx.lifecycle.ViewModel
import com.example.shopinglistmvvmcleanarchitecture.data.ShopListRepositoryImpl
import com.example.shopinglistmvvmcleanarchitecture.domain.DellItemUseCase
import com.example.shopinglistmvvmcleanarchitecture.domain.EditItemUseCase
import com.example.shopinglistmvvmcleanarchitecture.domain.GetShopListUseCase
import com.example.shopinglistmvvmcleanarchitecture.domain.ShopItem

class MainViewModel : ViewModel() {

    private val repository = ShopListRepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val dellItemUseCase = DellItemUseCase(repository)
    private val editItemUseCase = EditItemUseCase(repository)


    val  shopList = getShopListUseCase.getShopList()


    fun dellItem(shopItem: ShopItem){
        dellItemUseCase.dellItem(shopItem)
    }

    fun editItem(shopItem: ShopItem){
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editItemUseCase.editItem(newItem)
    }



}