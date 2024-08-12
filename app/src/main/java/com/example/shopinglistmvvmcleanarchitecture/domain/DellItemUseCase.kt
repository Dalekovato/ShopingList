package com.example.shopinglistmvvmcleanarchitecture.domain

class DellItemUseCase(private val shopListRepository: ShopListRepository) {

    suspend fun dellItem(shopItem: ShopItem) {
        shopListRepository.dellItem(shopItem)
    }

}