package com.example.shopinglistmvvmcleanarchitecture.domain


class AddItemUseCase(private val shopListRepository: ShopListRepository) {

    fun addItem(shopItem: ShopItem){
        shopListRepository.addItem(shopItem)
    }

}
