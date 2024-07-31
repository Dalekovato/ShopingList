package com.example.shopinglistmvvmcleanarchitecture.domain

import androidx.lifecycle.LiveData

interface ShopListRepository {


    fun addItem(shopItem: ShopItem)

    fun dellItem(shopItem: ShopItem)

    fun editItem(shopItem: ShopItem)

    fun getItemForId(shopItemId: Int) : ShopItem

    fun getShopList(): LiveData<List<ShopItem>>

}