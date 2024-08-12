package com.example.shopinglistmvvmcleanarchitecture.domain

import androidx.lifecycle.LiveData

interface ShopListRepository {


    suspend fun addItem(shopItem: ShopItem)

    suspend fun dellItem(shopItem: ShopItem)

    suspend fun editItem(shopItem: ShopItem)

    suspend fun getItemForId(shopItemId: Int) : ShopItem

    fun getShopList(): LiveData<List<ShopItem>>

}