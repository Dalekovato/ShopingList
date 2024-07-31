package com.example.shopinglistmvvmcleanarchitecture.domain

class GetItemForId (private val shopListRepository: ShopListRepository){

    fun getItemForId(shopItemId: Int) : ShopItem{

        return shopListRepository.getItemForId(shopItemId)

    }

}