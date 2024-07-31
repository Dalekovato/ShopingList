package com.example.shopinglistmvvmcleanarchitecture.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shopinglistmvvmcleanarchitecture.domain.ShopItem
import com.example.shopinglistmvvmcleanarchitecture.domain.ShopListRepository

object ShopListRepositoryImpl : ShopListRepository {

    private val shopListLD = MutableLiveData<List<ShopItem>>()
    private  val shopList = mutableListOf<ShopItem>()

    private var autoIncrementId = 0

    init {
        for (i in 0 until 10){
            val item = ShopItem("Name$i",i,true)
            addItem(item)
        }
    }

    override fun addItem(shopItem: ShopItem) {
        if(shopItem.id == ShopItem.UNDEFINED_ID){
            shopItem.id = autoIncrementId++
        }
        shopList.add(shopItem)
        updateList()
    }

    override fun dellItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
        updateList()
    }

    override fun editItem(shopItem: ShopItem) {
       val oldElement = getItemForId(shopItem.id)
        shopList.remove(oldElement)
        addItem(shopItem)
    }

    override fun getItemForId(shopItemId: Int): ShopItem {
        return shopList.find { it.id == shopItemId }
            ?: throw  RuntimeException("Element with id $shopItemId not found" )
    }

    override fun getShopList(): LiveData<List<ShopItem>> {
        return shopListLD
    }

    private fun updateList(){
        shopListLD.value = shopList.toList()
    }

}