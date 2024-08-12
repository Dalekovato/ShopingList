package com.example.shopinglistmvvmcleanarchitecture.data

import com.example.shopinglistmvvmcleanarchitecture.domain.ShopItem

class ShopListMapper {

    fun mapEntityToDbModel(shopItem: ShopItem)= ShopItemDbModel(
            id = shopItem.id ,
            name = shopItem.name,
            count = shopItem.count,
            enabled = shopItem.enabled
    )

    fun mapDbModetToEntity(shopItemDbModel: ShopItemDbModel )= ShopItem(
        id = shopItemDbModel.id ,
        name = shopItemDbModel.name,
        count =  shopItemDbModel.count,
        enabled = shopItemDbModel.enabled
    )

    fun mapListDbModelToListEntity(list: List<ShopItemDbModel>)= list.map{
        mapDbModetToEntity(it)
    }


}