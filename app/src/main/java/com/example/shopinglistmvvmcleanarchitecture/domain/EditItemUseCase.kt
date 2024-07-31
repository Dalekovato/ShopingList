package com.example.shopinglistmvvmcleanarchitecture.domain

class EditItemUseCase(private val shopListRepository: ShopListRepository) {

    fun editItem(shopItem: ShopItem){
            shopListRepository.editItem(shopItem)
    }

}