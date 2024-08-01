package com.example.shopinglistmvvmcleanarchitecture.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shopinglistmvvmcleanarchitecture.R
import com.example.shopinglistmvvmcleanarchitecture.domain.ShopItem

class ShopListAdapter: RecyclerView.Adapter<ShopListAdapter.ShopItemViewHolder>() {

    var shopList = listOf<ShopItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onShopItemLongClickListner: ((ShopItem)-> Unit)? = null
    var onShopItemClickListner: ((ShopItem)-> Unit)? = null

    class ShopItemViewHolder(val view:View ): RecyclerView.ViewHolder(view){
        val tvName = view.findViewById<TextView>(R.id.tv_name)
        val tvCount = view.findViewById<TextView>(R.id.tv_count)


    }

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
        val shopItem = shopList[position]

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
        val item = shopList[position]
        return if(item.enabled){
            VIEV_TYPE_ENABLED
        }else{
            VIEV_TYPE_DISALED
        }


    }


    override fun getItemCount(): Int {
        return shopList.size
    }


    companion object{
        const val VIEV_TYPE_ENABLED = 100
        const val VIEV_TYPE_DISALED = 101

        const val MAX_POOL_SIZE = 15
    }

}