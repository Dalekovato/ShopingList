package com.example.shopinglistmvvmcleanarchitecture.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.shopinglistmvvmcleanarchitecture.R
import com.example.shopinglistmvvmcleanarchitecture.domain.ShopItem

class ShopListAdapter() : ListAdapter<ShopItem,ShopItemViewHolder>(ShopItemDiffCallback()){

    var onShopItemLongClickListner: ((ShopItem)-> Unit)? = null
    var onShopItemClickListner: ((ShopItem)-> Unit)? = null

//    var shopList = listOf<ShopItem>()  Старый вариант обновления списка
//        set(value) {
//            val callback = ShopListDiffCallBack(shopList, value)
//            val diffResult = DiffUtil.calculateDiff(callback)
//            diffResult.dispatchUpdatesTo(this)
//            field = value
//
//        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {

        val  layout = when (viewType){
            VIEV_TYPE_DISALED -> R.layout.item_shop_disabled
            VIEV_TYPE_ENABLED -> R.layout.item_shop_enabled
            else->throw RuntimeException("Unknown view type: $viewType")
        }

        val view = LayoutInflater.from(parent.context).inflate(layout,parent,false)
        return ShopItemViewHolder(view)

    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val shopItem = getItem(position)

        holder.view.setOnLongClickListener {
            onShopItemLongClickListner?.invoke(shopItem)
            true
        }

        holder.view.setOnClickListener {
            onShopItemClickListner?.invoke(shopItem)
            true
        }

        holder.tvName.text = shopItem.name
        holder.tvCount.text = shopItem.count.toString()

    }

    override fun getItemViewType(position: Int): Int {

        val item = getItem(position)

        return if(item.enabled){
            VIEV_TYPE_ENABLED
        }else{
            VIEV_TYPE_DISALED
        }

    }

    companion object{
        const val VIEV_TYPE_ENABLED = 100
        const val VIEV_TYPE_DISALED = 101

        const val MAX_POOL_SIZE = 15
    }

}