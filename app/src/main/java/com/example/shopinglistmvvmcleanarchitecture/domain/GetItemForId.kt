package com.example.shopinglistmvvmcleanarchitecture.domain

class GetItemForId (private val shopListRepository: ShopListRepository){

    suspend fun getItemForId(shopItemId: Int) : ShopItem{

        return shopListRepository.getItemForId(shopItemId)

    }

}