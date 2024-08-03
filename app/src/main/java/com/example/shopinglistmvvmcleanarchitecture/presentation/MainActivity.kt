package com.example.shopinglistmvvmcleanarchitecture.presentation

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.shopinglistmvvmcleanarchitecture.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var shopListAdapter: ShopListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
        setupRecyclerView()
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.shopList.observe(this) {
            shopListAdapter.submitList(it)
        }

        val buttonAddItem = findViewById<FloatingActionButton>(R.id.button_add_shop_item)
        buttonAddItem.setOnClickListener {
            val intent = ShopItemActiviry.newIntentAddItem(this)
            startActivity(intent)
        }

    }

    private fun setupRecyclerView() {

        val rvShopList = findViewById<RecyclerView>(R.id.rv_shop_list)
        with(rvShopList) {
            shopListAdapter = ShopListAdapter()
            adapter = shopListAdapter
            recycledViewPool.setMaxRecycledViews(
                ShopListAdapter.VIEV_TYPE_ENABLED,
                ShopListAdapter.MAX_POOL_SIZE)
            recycledViewPool.setMaxRecycledViews(
                ShopListAdapter.VIEV_TYPE_DISALED,
                ShopListAdapter.MAX_POOL_SIZE)
        }

        setupLongClickListner()

        setupClickListner()

        setupSwipeListner(rvShopList)

    }

    private fun setupSwipeListner(rvShopList: RecyclerView?) {
        val callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT
                    or ItemTouchHelper.RIGHT) {

            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = shopListAdapter.currentList[viewHolder.adapterPosition]
                viewModel.dellItem(item)
            }
        }

        val iteTouchHelper = ItemTouchHelper(callback)
        iteTouchHelper.attachToRecyclerView(rvShopList)
    }

    private fun setupClickListner() {
        shopListAdapter.onShopItemClickListner = {
            val intent = ShopItemActiviry.newIntentEditItem(this, it.id)
            startActivity(intent)
        }
    }

    private fun setupLongClickListner() {
        shopListAdapter.onShopItemLongClickListner = {
            viewModel.editItem(it)
        }
    }

}